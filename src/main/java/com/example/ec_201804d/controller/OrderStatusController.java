package com.example.ec_201804d.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ec_201804d.form.OrderDetailForm;
import com.example.ec_201804d.repository.OrderRepository;

/**
 * 注文ステータス変更コントローラ.
 * 
 * @author takanori.noguchi
 *
 */
@Controller
@RequestMapping("/admin")
public class OrderStatusController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	HttpSession session;

	@ModelAttribute
	public OrderDetailForm setUpForm() {
		return new OrderDetailForm();
	}

	public Map<Integer, String> mapCreate() {
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(1, "未入金");
		map.put(2, "入金済み");
		map.put(3, "発送済み");
		map.put(9, "キャンセル");
		return map;
	}

	@RequestMapping(value = "/updateStatus")
	public String viewOrderDetail(@RequestParam Long id, OrderDetailForm form, RedirectAttributes redirect,
			Model model) {
		int status = Integer.parseInt(form.getStatus());
		orderRepository.updateStatus(status, id);
		String str = "更新されました。";
		redirect.addFlashAttribute("update", str);
		return "redirect:/admin/orderDetail?id=" + id;
	}

}