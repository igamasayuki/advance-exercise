package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Info;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文一覧を表示するコントローラ.
 * 
 * @author takanori.noguchi
 *
 */
@Controller
@RequestMapping("/admin")
public class OrderListController {

	@Autowired
	OrderRepository repository;

	@RequestMapping("/viewOrderList")
	public String list(Model model) {
		List<Order> order = repository.findAll();
		model.addAttribute("order", order);
		model.addAttribute("orderCheck",order.isEmpty());
		
		return "/orderList";
	}

	@RequestMapping("/orderDetail")
	public String execute(long id, Model model) {
		Order order = repository.load(id);
		List<Info> list = repository.find(id);
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		return "/orderDetail";
	}
}
