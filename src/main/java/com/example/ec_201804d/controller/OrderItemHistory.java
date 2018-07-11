package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 購入履歴を扱うコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Transactional
@Controller
@RequestMapping(value="/userorderhistory")
public class OrderItemHistory {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 購入履歴画面を表示する.
	 * 
	 * @param loginUser　利用者のログイン情報
	 * @param model　リクエストスコープ
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Long userId = loginUser.getUser().getId();
		List<Order> orderList = orderRepository.findByUserId(userId);
		if (orderList.isEmpty()) {
			return "orderHistory";
		}
		model.addAttribute("orderList", orderList);
		return "orderHistory";
	}
}