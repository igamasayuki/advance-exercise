package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.repository.OrderItemRepository;

/**
 * ショッピングカートの中身を削除するコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/user")
public class OrderItemDeletionController {
	/** 注文商品DBを操作するリポジトリ */
	@Autowired
	private OrderItemRepository repository;
	
	/**
	 * ショッピングカートの中身を削除する.
	 * @param id ID
	 * @return ショッピングカートの中身一覧の画面
	 */
	@RequestMapping(value="/deleteOrderItem")
	public String deleteOrderItem(long id) {
		repository.deleteById(id);
		return "redirect:/user/viewShoppingCart";
	}
}
