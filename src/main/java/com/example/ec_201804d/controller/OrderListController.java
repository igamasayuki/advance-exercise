package com.example.ec_201804d.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Info;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.form.OrderDetailForm;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文一覧を表示するコントローラ.
 * 
 * @author takanori.noguchi
 *
 */
@Transactional
@Controller
@RequestMapping("/admin")
public class OrderListController {

	@Autowired
	OrderRepository repository;
	
	@ModelAttribute
	public OrderDetailForm setUpItemForm() {
		return new OrderDetailForm();
	}

	@RequestMapping("/viewOrderList")
	public String list(Model model) {
		Map<Integer,String> statusMap = new LinkedHashMap<>();
		statusMap.put(-1, "");
		statusMap.put(0, "未購入");
		statusMap.put(1, "未入金");
		statusMap.put(2, "入金済み");
		statusMap.put(3, "発送済み");
		statusMap.put(9, "キャンセル");
		model.addAttribute("statusMap", statusMap);
		
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
