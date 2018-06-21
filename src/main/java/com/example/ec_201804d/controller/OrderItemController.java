package com.example.ec_201804d.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.OrderItemRepository;
import com.example.ec_201804d.repository.OrderRepository;

@Controller
@RequestMapping("/orderItems")
public class OrderItemController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping("/addItem")
	public String addItemToShoppingCart(ItemForm itemForm, RedirectAttributes redirectAttributes) {
		
		Order order;
		
		long userId;
		if((session.getAttribute("user")) == null) {
			userId = Long.parseLong(session.getId());
		}else {
			userId = (((User)session.getAttribute("user")).getId());
		}
		
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
		}
			long orderId = orders.get(0).getId();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemForm.getItemId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(itemForm.getQuantity());
		orderItemRepository.save(orderItem);
		
		return "redirect:/shoppingCart/showShoppingCart";
	}

}
