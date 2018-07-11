package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品詳細を表示するためのコントローラー.
 * 
 * @author minori.matsuoka
 *
 */
@Transactional
@Controller
@RequestMapping("/user")
public class ItemDetailController {

	@ModelAttribute
	public ItemForm setupForm() {
		return new ItemForm();
	}

	@Autowired
	ItemRepository repository;

	/**
	 * 商品詳細画面に遷移.
	 * 
	 * @param id 商品ID
	 * @param model　リクエストスコープ
	 * @return　商品詳細画面
	 */
	@RequestMapping("/item_detail")
	public String detail(Long id, Model model) {
		Item item = repository.load(id);
		model.addAttribute("item", item);
		return "/itemDetail";
	}

}
