package com.example.ec_201804d.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotBlank;

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
import com.example.ec_201804d.form.ItemRegistrationForm;
import com.example.ec_201804d.repository.ItemRepository;

/**
 * 商品登録画面を表示するコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@Transactional
@RequestMapping(value="/item_registration")
public class ItemRegistrationController {
	/** アプリケーションスコープ */
	@Autowired
	private ServletContext application;
	/** 商品DBを扱うリポジトリ */
	@Autowired
	private ItemRepository repository;

	/**
	 * 商品登録フォームの初期化.
	 * @return 商品登録フォーム
	 */
	@ModelAttribute
	public static ItemRegistrationForm setUpForm() {
		return new ItemRegistrationForm();
	}
	
	/**
	 * 商品登録画面を表示する.
	 * @param model リクエストスコープ
	 * @return
	 */
	@RequestMapping(value="/show_view")
	public String showInsertItemView(Model model) {
		return "insertItem";
	}
	
	/**
	 * 商品を登録する.
	 * @param form 商品登録フォーム
	 * @param result 入力チェックの結果
	 * @param model リクエストスコープ
	 * @return 入力が正しければ管理者の商品一覧画面、入力チェックに引っかかれば商品登録画面を表示する
	 */
	@RequestMapping(value="/register")
	public String registerItem(@Validated ItemRegistrationForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showInsertItemView(model);
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		MultipartFile imageFile = form.getImageFile();
		System.out.println("fileName : " + form.getImageFile());
		if (imageFile.isEmpty()) {
			model.addAttribute("imageError", "表示する商品画像を選択してください");
			return showInsertItemView(model);
		}
		try {
			String fileName = imageFile.getOriginalFilename();
			String destPath = application.getRealPath("/img/" + fileName);
			File destFile = new File(destPath);
			imageFile.transferTo(destFile);
			item.setImagePath(fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return showInsertItemView(model);
		} catch (IOException e) {
			e.printStackTrace();
			return showInsertItemView(model);
		}
		repository.insert(item);
		return "redirect:/adminItemList/viewItemList";
	}
}
