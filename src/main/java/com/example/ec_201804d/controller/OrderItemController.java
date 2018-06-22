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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.OrderItemRepository;
import com.example.ec_201804d.repository.OrderRepository;

@Controller
@RequestMapping("/user")
public class OrderItemController {

	@ModelAttribute(value= "itemForm")
	public ItemForm setupForm() {
		return new ItemForm();
	}
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping("/view")
	public String viewShoppingCart() {
		return "redirect:/user/viewShoppingCart";
	}

	@RequestMapping("/addItem")
	public String addItemToShoppingCart(@AuthenticationPrincipal LoginUser loginUser, @Validated ItemForm itemForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) {

		if(result.hasErrors()) {
			return "/user/item_detail?id=" + itemForm.getItemId();
		}
		
		Order order;
		
		long userId;
		if(loginUser == null) {
//			userId = Long.parseLong(session.getId());
			userId = session.getId().hashCode();
		}else {
//			userId = (((User)session.getAttribute("user")).getId());
			userId = loginUser.getUser().getId();
		}
		System.out.println("id:" + userId);
		
		List<Order> orders = orderRepository.findByUserIdAndStatus(userId, 0);
		if(orders.isEmpty()) {
			order = new Order();
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			Date date = Date.valueOf(localDate);
			String orderNumber = (String)(localDate.format(formatter)) + "123456";
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
		orderItem.setItemId(itemForm.getItemId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(itemForm.getQuantity());
		orderItemRepository.save(orderItem);
		
		return "forward:/user/view";
	}

}
