package com.example.ec_201804d.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * ショッピングカートを表示するコントローラー.
 * 
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
	 * 
	 * @return toViewShoppingCartメソッド
	 */
	@RequestMapping("/viewShoppingCart")
	public String findByUserId(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// sessionIDを数値に変換する
		// System.err.println(sessionID:" + session.getId());
		// String sessionStr = session.getId();
		// long sessionId = sessionStr.hashCode();
		// System.out.println("sessionIdをハッシュ化後" + sessionId);
			
		long id = loginUser.getUser().getId();
		
		List<Order> orderList = repository.findByUserIdAndStatus(id,0);
		
		if(orderList.isEmpty()) {
			return  "viewShoppingCart";
		}

		model.addAttribute("orderItemList", orderList.get(0).getOrderItemList());
		return "viewShoppingCart";
	}

}
