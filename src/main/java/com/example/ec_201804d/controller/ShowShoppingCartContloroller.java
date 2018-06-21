package com.example.ec_201804d.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.repository.OrderRepository;

/** 
 * ショッピングカートを表示するコントローラー.
 * @author minori.matsuoka
 *
 */
@Controller
@RequestMapping("/user")
public class ShowShoppingCartContloroller {
	
	@Autowired
	HttpSession session;


	/** 注文一覧レポジトリ. */
	@Autowired
	OrderRepository repository;
	
	/**
	 * ショッピングカート一覧を取得する.
	 * @return toViewShoppingCartメソッド
	 */
	@RequestMapping("/viewShoppingCart")
	public String findByUserId(Model model) {
		
	
		long sessionId = Long.parseLong(session.getId());
//		long sessionId = 3;
		
//		long sessionId = session.getId();
		List<Order> orderList = repository.findByUserIdAndStatus(sessionId,0);
		System.err.println(orderList.get(0).getOrderItemList().get(0).getItem().getName());
		if(orderList.isEmpty()) {
			return  "viewShoppingCart";
		}
		
		model.addAttribute("orderItemList",orderList.get(0).getOrderItemList());
		return "viewShoppingCart";
	}
	

}
