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
		
		List<Order> orderItemList = repository.findByUserIdAndStatus(sessionId,0);
		
		model.addAttribute("orderItem",orderItemList);
		return "redirect:/toViewShoppingCart";
	}
	
	/**
	 * ショッピングカート一覧を表示する
	 * @return　ショッピングカートjsp
	 */
	public String toViewShoppingCart() {
		return "/viewShoppingCart";
	}
}
