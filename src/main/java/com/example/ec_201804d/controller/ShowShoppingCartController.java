package com.example.ec_201804d.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.ec_201804d.domain.LoginUser;
import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.domain.OrderItem;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * ショッピングカートを表示するコントローラー.
 * 
 * @author minori.matsuoka
 *
 */
@Transactional
@Controller
@RequestMapping("/user")
@SessionAttributes(types= {OrderItem.class})
public class ShowShoppingCartController {

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
		long id;
		if (loginUser == null) {
			String sessionStr = session.getId();
			id = sessionStr.hashCode();
		} else {			
			id = loginUser.getUser().getId();
		}
			
		
		List<Order> orderList = repository.findByUserIdAndStatus(id,0);
		if(orderList.isEmpty()) {
			return  "viewShoppingCart";
		}
		Order order = orderList.get(0);
		if (order.getOrderItemList().get(0).getId()==0) {
			return  "viewShoppingCart";			
		}
		model.addAttribute("orderItemList", order.getOrderItemList());
		return "viewShoppingCart";
	}

}
