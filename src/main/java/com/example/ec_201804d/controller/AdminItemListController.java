package com.example.ec_201804d.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 管理者の商品一覧画面を表示するコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/admin")
public class AdminItemListController {
	/** 商品DBを扱うリポジトリ */
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 管理者の商品一覧画面を表示する.
	 * @param model リクエストスコープ
	 * @return 管理者の商品一覧画面
	 */
	@RequestMapping(value="/adminItemList")
	public String showItemListView(Model model) {
		List<Item> itemList = repository.findAll();
		if (itemList.isEmpty()) return "adminItemList";
		model.addAttribute("items", itemList);
		return "adminItemList";
	}
	
	/**
	 * 商品の文字列検索を行い、商品一覧画面を表示する.
	 * @param keyword 検索文字列
	 * @param model リクエストスコープ
	 * @return 管理者の商品一覧画面
	 */
	@RequestMapping(value="/index")
	public String index(String keyword, Model model) {
		if (keyword == null) {
			keyword = "";
		}
		model.addAttribute("keyword", keyword);
		List<Item> filterdItemList = new ArrayList<>();
		for (String word : KanaChangeService.changeWord(keyword)) {
			filterdItemList.addAll(repository.findSaleItemsByWord(word));
		}
		if (filterdItemList.isEmpty()) return "adminItemList";
		model.addAttribute("items", filterdItemList);
		return "adminItemList";
	}
}
