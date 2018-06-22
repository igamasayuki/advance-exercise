package com.example.ec_201804d.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品一覧画面を表示するコントローラー.
 * 
 * @author minori.matsuoka
 *
 */
@Controller
@RequestMapping("/user")
public class ItemListController {

	/** 商品DBをあつかうリポジトリ */
	@Autowired
	ItemRepository repository;

	@Autowired
	HttpSession session;

	/**
	 * 利用者の商品一覧画面を表示する.
	 * 
	 * @param model
	 *            リクエストスコープ
	 * @return 利用者が見ることができる商品情報
	 */
	@RequestMapping("/viewItemList")
	public String list(Model model) {

		// sessionIDを数値に変換する
		// System.err.println("ItemControllerのsessionID:" + session.getId());
		// String sessionStr = session.getId();
		// long sessionId = sessionStr.hashCode();
		// System.out.println("sessionIdをハッシュ化後" + sessionId);

		List<Item> itemList = repository.findSaleItems();

		if (itemList.isEmpty()) {
			return "/itemList";
		}
		model.addAttribute("itemList", itemList);
		return "/itemList";
	}

	/**
	 * 商品の文字列検索を行い、商品一覧を表示する.
	 * 
	 * @param word
	 *            検索された文字列
	 * @param model
	 *            リクエストスコープ
	 * @return 該当する商品情報
	 */
	@RequestMapping("/findItem")
	public String findItem(String word, Model model) {
		List<Item> findItemList = repository.findSaleItemsByWord(word);
		model.addAttribute("findItemList", findItemList);
		if (findItemList.isEmpty()) {
			return "/itemList";
		}
		model.addAttribute("itemList", findItemList);
		return "/itemList";
	}
}
