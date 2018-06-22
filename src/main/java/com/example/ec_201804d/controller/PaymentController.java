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

@Controller
@Transactional
@RequestMapping(value="/userPayment")
public class PaymentController {
	@Autowired
	HttpSession session;
	@Autowired
	private OrderRepository repository;
	@Autowired
	private UserRepository userRepository;
	
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
		int totalPrice = 0;
		for (OrderItem orderItem : order.getOrderItemList()) {
			totalPrice += orderItem.getItem().getPrice() * orderItem.getQuantity();
		}
		order.setTotalPrice(totalPrice);
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
	
	@RequestMapping(value="/showView")
	public String showPaymentCloseOutView() {
		return "paymentCloseOut";
	}
	
	@RequestMapping(value="/closeOut")
	public String closeOutPayment(long orderId) {
		Order order = repository.load(orderId);
		order.setStatus(1);
		repository.update(order);
		return "redirect:showView";
	}
}
