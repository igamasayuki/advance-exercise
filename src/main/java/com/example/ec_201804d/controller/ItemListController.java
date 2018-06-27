package com.example.ec_201804d.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.form.PagingForm;
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
	
	@ModelAttribute
	public PagingForm setUpPagingForm() {
		return new PagingForm();
	}
	

	/**
	 * 利用者の商品一覧画面を表示する.
	 * 
	 * @param model
	 *            リクエストスコープ
	 * @return 利用者が見ることができる商品情報
	 */
	@RequestMapping("/viewItemList")
	public String list(Model model,PagingForm form) {

		// sessionIDを数値に変換する
		// System.err.println("ItemControllerのsessionID:" + session.getId());
		// String sessionStr = session.getId();
		// long sessionId = sessionStr.hashCode();
		// System.out.println("sessionIdをハッシュ化後" + sessionId);

		List<Item> itemList = repository.findSaleItems();
		ArrayList<Integer>numberList=new ArrayList<Integer>();
		for(int i=0;i<=(int)(itemList.size()/8);i++) {
			numberList.add(i+1);
		}
		if (itemList.isEmpty()) {
			return "/itemList";
		}
		if(form.getPaging()==null) {
			form.setPaging(1);
		}
		
		model.addAttribute("begin",(form.getPaging()-1)*8);
		model.addAttribute("end",form.getPaging()*8-1);
		model.addAttribute("numberList",numberList);
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
	public String findItem(String word, Model model,PagingForm form) {
		List<Item> findItemList = repository.findSaleItemsByWord(word);
		model.addAttribute("findItemList", findItemList);
		if (findItemList.isEmpty()) {
			return "/itemList";
		}
		if(form.getPaging()==null) {
			form.setPaging(1);
		}
		ArrayList<Integer>numberList=new ArrayList<Integer>();
		for(int i=0;i<=(int)(findItemList.size()/8);i++) {
			numberList.add(i+1);
		}
		model.addAttribute("begin",(form.getPaging()-1)*8);
		model.addAttribute("end",form.getPaging()*8-1);
		model.addAttribute("numberList",numberList);
		model.addAttribute("itemList", findItemList);
		return "/itemList";
	}
}
