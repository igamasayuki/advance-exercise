package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品を削除フラグをtrueにするコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/itemDeletion")
public class ItemDeletionSwitchController {
	/** 商品リポジトリ */
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品を削除する.
	 * 商品の削除フラグをtrueに切り替える
	 * @param id ID
	 * @return 管理者の商品一覧画面
	 */
	@RequestMapping(value="/delete")
	public String deleteItem(long id) {
		repository.updateDeletedById(id, false);
		return "redirect:/adminItemList/";
	}
	
	/**
	 * 商品を再表示させる.
	 * 商品の削除フラグをfalseに切り替える.
	 * @param id ID
	 * @return 管理者の商品一覧画面
	 */
	@RequestMapping(value="/redisplay")
	public String redisplayItem(long id) {
		repository.updateDeletedById(id, true);
		return "redirect:/adminItemList/";
	}
	
}
