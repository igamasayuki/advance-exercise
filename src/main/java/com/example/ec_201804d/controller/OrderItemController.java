package com.example.ec_201804d.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.OrderItemRepository;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文商品情報を扱うコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@RequestMapping("/user")
public class OrderItemController {

	/**
	 * 商品情報のフォームを初期化する.
	 * @return 商品情報のフォーム
	 */
	@ModelAttribute
	public ItemForm setupForm() {
		return new ItemForm();
	}
	
	/** セッションスコープ */
	@Autowired
	private HttpSession session;
	
	/** 注文商品DBを操作するリポジトリ */
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/** 注文DBを操作するリポジトリ */
	@Autowired
	private OrderRepository orderRepository;
	
	/** 商品詳細を扱うコントローラ */
	@Autowired
	private ItemDetailController itemDetailController;
	
	/**
	 * ショッピングカートを表示する.
	 * @return
	 */
	@RequestMapping("/view")
	public String viewShoppingCart() {
		return "redirect:/user/viewShoppingCart";
	}

	/**
	 * 商品をショッピングカートに追加する.
	 * @param loginUser ログインしているユーザ
	 * @param itemForm 商品情報フォーム
	 * @param result 入力値チェックの結果
	 * @param model リクエストスコープ
	 * @return
	 */
	@RequestMapping("/addItem")
	public String addItemToShoppingCart(@AuthenticationPrincipal LoginUser loginUser, @Validated ItemForm itemForm, BindingResult result, Model model) {

		if(result.hasErrors()) {
			System.out.println("ショッピングカート入力チェックif文");
//			return "redirect:/user/item_detail?id=" + itemForm.getItemId();
//			return "forward:/user/item_detail";
			return itemDetailController.detail(itemForm.getId(), model);
		}
		
		Order order;
		
		long userId;
		if(loginUser == null) {
			userId = session.getId().hashCode();
		}else {
			userId = loginUser.getUser().getId();
		}
		
		List<Order> orders = orderRepository.findByUserIdAndStatus(userId, 0);
		if(orders.isEmpty()) {
			order = new Order();
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			Date date = Date.valueOf(localDate);
			boolean isSeqReset = (localDate.getYear() != orderRepository.findRecentOrderYear());
			int number = orderRepository.nextNumberSeq(isSeqReset);
			String orderNumber = (String)(localDate.format(formatter)) + String.format("%06d", number);
			order.setOrderNumber(orderNumber);
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			order.setOrderDate(date);
			orderRepository.insertNewOrder(order);
			orders = orderRepository.findByUserIdAndStatus(userId, 0);
		}
			long orderId = orders.get(0).getId();
		OrderItem orderItem = new OrderItem();
		
		List<OrderItem> orderItemList = orders.get(0).getOrderItemList();
		int sumQuantity = 0;
		for (OrderItem orderItem2 : orderItemList) {
			sumQuantity += orderItem2.getQuantity();
		}
		int itemQuantity = itemForm.getIntQuantity();
		sumQuantity = sumQuantity + itemQuantity;
		if(sumQuantity >= 1001) {
			result.rejectValue("quantity", null, "カートに入れられる個数は1,000個までです");
			String text = "現在、カートにないっている商品の個数は" + (sumQuantity-itemQuantity) + "個です";
			result.rejectValue("quantity", null, text);
			return itemDetailController.detail(itemForm.getId(), model);
		}
		orderItem.setItemId(itemForm.getId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(itemForm.getIntQuantity());
		orderItemRepository.save(orderItem);
		
		return "forward:/user/view";
	}

}
