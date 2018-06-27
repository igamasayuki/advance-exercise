package com.example.ec_201804d.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Order;
import com.example.ec_201804d.form.OrderDetailForm;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文をステータスで検索するコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Controller
@RequestMapping(value="/admin")
public class SearchOrderByStatus {

	@Autowired
	private OrderRepository orderRepository;
	
	@ModelAttribute
	public OrderDetailForm setUpItemForm() {
		return new OrderDetailForm();
	}
	
	/**
	 * 注文をステータスで検索
	 * @param status 注文ステータス
	 * @param model　リクエストスコープ
	 * @return　注文一覧画面
	 */
	@RequestMapping(value="/statussearch")
	public String searchOrderByStatus(String status, Model model) {
		Integer orderStatus = Integer.parseInt(status);
		if(orderStatus == -1) {
			return "forward:/admin/viewOrderList";
		}
		Map<Integer,String> statusMap = new LinkedHashMap<>();
		statusMap.put(-1, "");
		statusMap.put(0, "未購入");
		statusMap.put(1, "未入金");
		statusMap.put(2, "入金済み");
		statusMap.put(3, "発送済み");
		statusMap.put(9, "キャンセル");
		model.addAttribute("statusMap", statusMap);
		
		List<Order> orderList = orderRepository.findByOrderStatus(orderStatus);
		model.addAttribute("order", orderList);
		model.addAttribute("emptyOrderList", orderList.isEmpty());
		return "orderList";
	}
}
