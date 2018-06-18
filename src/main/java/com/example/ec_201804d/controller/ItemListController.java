package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品一覧を表示するコントローラー.
 * 
 * @author minori.matsuoka
 *
 */
@Controller
@RequestMapping("/itemList")
public class ItemListController {

	@Autowired
	ItemRepository repository;
	
	@RequestMapping("/viewItemList")
	public String list(Model model) {
		List<Item> itemList = repository.findAll();
		model.addAttribute("itemList",itemList);
		return "/itemList";
	}
}