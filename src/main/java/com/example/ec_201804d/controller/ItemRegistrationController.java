package com.example.ec_201804d.controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping(value="/admin")
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
	 * @throws IOException 
	 */
	@RequestMapping(value="/register")
	public String registerItem(@Validated ItemRegistrationForm form, BindingResult result, Model model, RedirectAttributes redirect) throws IOException {
		if(repository.countNumberOfSameName(form.getName()) > 0) {
			result.rejectValue("name", null, "すでに同じ名前で商品が登録されています");
		}
		MultipartFile imageFile = form.getImageFile();
		String fileName = imageFile.getOriginalFilename();
		if (imageFile.isEmpty()) {
			result.rejectValue("imageFile", null, "表示する商品画像を選択してください");
		} else {
			String extesion = fileName.split(Pattern.quote("."))[1];
			if (!"jpg".equals(extesion) && !"jpeg".equals(extesion)) {
				result.rejectValue("imageFile", null, "JPEGファイルを選択してください");
			}
			if (imageFile.getBytes().length >= 100000) {
				result.rejectValue("imageFile", null, "100KB未満のJPEGファイルを選択してください");
			}
		}
		if (result.hasErrors()) {
			return showInsertItemView(model);
		}			
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());
		try {
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
		redirect.addFlashAttribute("message", "登録が完了しました。");
		return "redirect:/admin/adminItemList";
	}
}
