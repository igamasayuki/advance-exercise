package com.example.ec_201804d.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.OrderItemRepository;

@Controller
@RequestMapping("/orderItems")
public class OrderItemController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@RequestMapping("/addItemToShoppingCart")
	public String addItemToShoppingCart(ItemForm itemForm) {
		
		long userId;
		if(session == null) {
			userId = Long.parseLong(session.getId());
		}else {
			userId = (((User)session.getAttribute("user")).getId());
		}
		
		long orderId = orderItemRepository.findByUserIdAndStatus(userId, 0).getId();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemForm.getItemId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(itemForm.getQuantity());
		orderItemRepository.save(orderItem);
		
		return "redirect:/";
	}

	@RequestMapping("/showShoppingCart")
	public String showShoppingCart() {
		return "redirect:/";
	}

}
