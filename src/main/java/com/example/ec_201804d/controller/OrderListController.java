package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文一覧を表示するコントローラ.
 * 
 * @author takanori.noguchi
 *
 */
@Controller
@RequestMapping("/orderList")
public class OrderListController {
	
	@Autowired
	OrderRepository repository;
	
	@RequestMapping("/viewOrderList")
	public String list(Model model) {
		List<Order> orderList = repository.findAll();
		model.addAttribute("orderList",orderList);
		return "/orderList";
	}
}
