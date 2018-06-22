package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.repository.OrderItemRepository;

@Controller
@Transactional
@RequestMapping(value="/user")
public class OrderItemDeletion {
	@Autowired
	private OrderItemRepository repository;
	
	@RequestMapping(value="/deleteOrderItem")
	public String deleteOrderItem(long id) {
		repository.deleteById(id);
		return "redirect:/user/viewShoppingCart";
	}
}
