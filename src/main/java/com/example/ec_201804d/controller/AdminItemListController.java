package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.repository.ItemRepository;

@Controller
@Transactional
@RequestMapping(value="/adminItemList")
public class AdminItemListController {
	@Autowired
	private ItemRepository repository;
	
	@RequestMapping
	public String showItemListView(Model model) {
		List<Item> itemList = repository.findAll();
		if (itemList.isEmpty()) return "adminItemList";
		model.addAttribute("items", itemList);
		return "adminItemList";
	}
	
	@RequestMapping(value="/index")
	public String index(String keyword, Model model) {
		model.addAttribute("keyword", keyword);
		List<Item> filterdItemList = repository.findByWord(keyword);
		System.out.println(filterdItemList);
		if (filterdItemList.isEmpty()) return "adminItemList";
		model.addAttribute("items", filterdItemList);
		return "adminItemList";
	}
}
