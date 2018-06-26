package com.example.ec_201804d.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.repository.OrderRepository;
import com.example.ec_201804d.repository.UserRepository;

/**
 * 決済を行うコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/userPayment")
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
	
	/**
	 * 決済確認画面を表示する.
	 * @param loginUser ログイン中のユーザ
	 * @param model リクエストスコープ
	 * @return 決済確認画面
	 */
	@RequestMapping
	public String showPaymentConfirmationView(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		long userId = loginUser.getUser().getId();
		List<Order> orders = repository.findByUserIdAndStatus(userId, 0);
		Order order = null;
		if (orders.isEmpty()) {
			return "viewItemList";
		} else {
			order = orders.get(0);
		}
		int sumPrice = 0;
		for (OrderItem orderItem : order.getOrderItemList()) {
			sumPrice += orderItem.getItem().getPrice() * orderItem.getQuantity();
		}
		order.setTotalPrice((int)(sumPrice*1.08 + 500));
		User user = userRepository.load(userId);
		order.setDeliveryName(user.getName());
		order.setDeliveryEmail(user.getEmail());
		order.setDeliveryZipCode(user.getZipCode());
		order.setDeliveryAddress(user.getAddress());
		order.setDeliveryTel(user.getTelephone());
		repository.update(order);
		
		model.addAttribute("order", order);
		return "paymentConfirmation";
	}
	
	/**
	 * 決済完了画面を表示する.
	 * @return
	 */
	@RequestMapping(value="/showView")
	public String showPaymentCloseOutView() {
		return "paymentCloseOut";
	}
	
	/**
	 * 決済を確定させる.
	 * @param orderId 注文ID
	 * @return 決済完了画面
	 */
	@RequestMapping(value="/closeOut")
	public String closeOutPayment(long orderId) {
		Order order = repository.load(orderId);
		order.setStatus(1);
		repository.update(order);
		return "redirect:showView";
	}
}
