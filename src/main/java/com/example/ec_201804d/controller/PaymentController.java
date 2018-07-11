package com.example.ec_201804d.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ec_201804d.domain.Info;
import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.domain.ResponseCancelAPIDomain;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.CardDetailInfoForm;
import com.example.ec_201804d.form.OrderNumberForm;
import com.example.ec_201804d.form.UserRegistrationForm;
import com.example.ec_201804d.repository.OrderRepository;
import com.example.ec_201804d.repository.UserRepository;
import com.example.ec_201804d.service.CallCancelApiService;

/**
 * 決済を行うコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/userPayment")
public class PaymentController {
	/** セッションスコープ */
	@Autowired
	HttpSession session;
	/** 注文DBを操作するレポジトリ */
	@Autowired
	private OrderRepository repository;
	/** 利用者DBを操作するリポジトリ */
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailSendController mailSendController;
	@Autowired
	private CallCancelApiService callCancelApiService;

	@ModelAttribute
	public UserRegistrationForm setUupUserRegistrationForm() {
		return new UserRegistrationForm();
	}
	@ModelAttribute
	public OrderNumberForm serUpOrderNumberForm() {
		return new OrderNumberForm();
	}

	@ModelAttribute
	public CardDetailInfoForm setUpCardPaymentForm() {
		return new CardDetailInfoForm();
	}

	/**
	 * 決済確認画面を表示する.
	 * 
	 * @param loginUser
	 *            ログイン中のユーザ
	 * @param model
	 *            リクエストスコープ
	 * @return 決済確認画面
	 */
	@RequestMapping
	public String showPaymentConfirmationView(@AuthenticationPrincipal LoginUser loginUser, Model model,
			UserRegistrationForm form, Integer payStatus) {
		long userId = loginUser.getUser().getId();
		List<Order> orders = repository.findByUserIdAndStatus(userId, 0);
		Order order = null;
		if (orders.isEmpty()) {
			return "viewItemList";
		} else {
			order = orders.get(0);
		}
		// ショッピングカートに入っている商品の個数を取得
		List<OrderItem> orderItemList = order.getOrderItemList();
		Integer quantity = 0;
		for (OrderItem orderItem : orderItemList) {
			quantity += orderItem.getQuantity();
		}
		if (quantity >= 1001) {
			System.out.println("quantity >= 1001");
			return "redirect:/user/viewShoppingCart";
		}

		int sumPrice = 0;
		for (OrderItem orderItem : order.getOrderItemList()) {
			sumPrice += orderItem.getItem().getPrice() * orderItem.getQuantity();
		}
		order.setTotalPrice((int) (sumPrice * 1.08 + 500));

		// 注文番号をスコープに格納
		String orderNumber = order.getOrderNumber();
		session.setAttribute("orderNumber", orderNumber);

		// お届け先の住所変更
		User user = userRepository.load(userId);
		order.setDeliveryName(user.getName());
		order.setDeliveryEmail(user.getEmail());
		if (form.getZipCode() != null) {
			order.setDeliveryZipCode(form.getZipCode());
			order.setDeliveryAddress(form.getAddress());
		} else {
			order.setDeliveryZipCode(user.getZipCode());
			order.setDeliveryAddress(user.getAddress());
		}
		order.setDeliveryTel(user.getTelephone());
		repository.update(order);

		// 支払方法選択のラジオボタン作成
		Map<Integer, String> paymentmethod = new LinkedHashMap<>();
		paymentmethod.put(0, "銀行振込");
		paymentmethod.put(1, "カード決済");
		model.addAttribute("paymentmethod", paymentmethod);

		model.addAttribute("order", order);
		return "paymentConfirmation";
	}

	/**
	 * APIの処理を実行
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/paymentApiResponse")
	public String confirmApiResponse(RedirectAttributes redirectAttributes) {
		String status = (String) session.getAttribute("paymentApiResponse");
		System.out.println(status);
		if (status.equals("success")) {
			return "redirect:/userPayment/closeOut";
		}
		redirectAttributes.addFlashAttribute("cardInfoError", "カード情報が不正です");
		return "redirect:/userPayment/";
	}

	/**
	 * 決済完了画面を表示する.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showView")
	public String showPaymentCloseOutView() {
		return "paymentCloseOut";
	}

	/**
	 * 決済を確定させる.
	 * 
	 * @param orderId
	 *            注文ID
	 * @param loginUser
	 *            ログインユーザの情報
	 * @return 決済完了画面
	 */
	@RequestMapping(value = "/closeOut")
	public String closeOutPayment(@AuthenticationPrincipal LoginUser loginUser, RedirectAttributes redirectAttributes) {

		long orderId = (long) session.getAttribute("orderId");
		List<Info> orderList = repository.find(orderId);

		Order order = repository.load(orderId);
		order.setStatus(1);
		repository.update(order);

		try {
			// 注文確定メール送信
			String mailAddress = loginUser.getUser().getEmail();
			mailSendController.sendOrderMail(mailAddress, orderList);
		} catch (MailAuthenticationException me) {
			// ロールバック処理を行う(WebAPIの呼び出し)
			me.printStackTrace();
			
			String orderNumber = order.getOrderNumber();
			OrderNumberForm form = new OrderNumberForm();
			form.setOrder_number(orderNumber);
			
			
			ResponseCancelAPIDomain responseCanselApiDomain = callCancelApiService.cancelApiService(form);
			String status = responseCanselApiDomain.getStatus();
			
			System.out.println("status: " + status);
			
			throw new RuntimeException();
		}

		return "redirect:showView";
	}
	
	/**
	 * キャンセルAPIを呼び出すメソッドに注文番号を送信する
	 * @return
	 */
	@RequestMapping(value="/toCallCancelApi")
	public String toCallCancelApi(RedirectAttributes redirectAttributes, @ModelAttribute("order_number") String orderNumber) {
		redirectAttributes.addFlashAttribute("order_number", orderNumber);
		return "redirect:/userCancelApi/callCancelApi";
	}

	/**
	 * お届け先住所入力画面に遷移する
	 * @return　お届け先住所入力画面
	 */
	@RequestMapping(value = "/toChangeAddress")
	public String toChangeAddress() {
		return "changeAddress";
	}
}
