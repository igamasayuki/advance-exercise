package com.example.ec_201804d.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.form.ItemEditingForm;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品編集画面を表示するコントローラー.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/itemEditing")
public class ItemEditingController {
	/** アプリケーションサーブレット */
	@Autowired
	private ServletContext application;
	/** 商品DBを扱うリポジトリ */
	@Autowired
	private ItemRepository repository;
	
	/**
	 * 商品編集フォームの初期化.
	 * @return 商品編集フォーム
	 */
	@ModelAttribute
	public ItemEditingForm setUpForm() {
		return new ItemEditingForm();
	}

	/**
	 * 商品編集画面を表示する.
	 * @param id ID
	 * @param form 商品編集フォーム
	 * @param model リクエストスコープ
	 * @return 商品編集画面
	 */
	@RequestMapping
	public String showInsertItemView(int id, ItemEditingForm form, Model model) {
		Item item = repository.load(id);
		BeanUtils.copyProperties(item, form);
		model.addAttribute("imagePath", item.getImagePath());
		return "itemEditing";
	}
	
	/**
	 * 商品情報を編集する.
	 * @param form 商品編集フォーム
	 * @param result 入力チェックの結果
	 * @param model リクエストスコープ
	 * @return 入力が正しければ管理者の商品一覧画面、入力チェックに引っかかると商品編集画面に戻る
	 */
	@RequestMapping(value="/edit")
	public String edit(@Validated ItemEditingForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "itemEditing";
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		MultipartFile imageFile = form.getImageFile();
		if (imageFile.isEmpty()) {
			item.setImagePath(form.getBeforeImagePath());
		} else {
			try {
				String fileName = imageFile.getOriginalFilename();
				String destPath = application.getRealPath("/img/" + fileName);
				File destFile = new File(destPath);
				imageFile.transferTo(destFile);
				item.setImagePath(fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				return "itemEditing";
			} catch (IOException e) {
				e.printStackTrace();
				return "itemEditing";
			}
		}
		repository.update(item);
		return "redirect:/adminItemList/";
	}
}
