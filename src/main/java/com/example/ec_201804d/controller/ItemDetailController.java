package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.form.ItemForm;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品詳細を表示するためのコントローラー
 * @author minori.matsuoka
 *
 */
@Controller
@RequestMapping("/user")
public class ItemDetailController {
	
	@ModelAttribute
	public ItemForm setupForm() {
		return new ItemForm();
	}

	@Autowired
	ItemRepository repository;
	
	@RequestMapping("/item_detail")
	public String detail(Integer id,Model model) {
	Item item = repository.load(id);
	model.addAttribute("item", item);
	return "/itemDetail";
	}
	
	
	}
