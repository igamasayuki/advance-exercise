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
@RequestMapping(value="/admin")
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
	@RequestMapping(value="/")
	public String showInsertItemView(long id, ItemEditingForm form, Model model) {
		Item item = repository.load(id);
		BeanUtils.copyProperties(item, form);
		form.setPrice(item.getPrice().toString());
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
	public String edit(@Validated ItemEditingForm form, BindingResult result, Model model, RedirectAttributes redirect) {
		if(repository.countDifferentIdOfSameName(form.getName(), form.getId()) > 0) {
			result.rejectValue("name", null, "すでに同じ名前で商品が登録されています");
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		MultipartFile imageFile = form.getImageFile();
		String fileName = imageFile.getOriginalFilename();
		if (imageFile.isEmpty()) {
			item.setImagePath(form.getBeforeImagePath());
		} else {
			String extesion = fileName.split(Pattern.quote("."))[1];
			if (!"jpg".equals(extesion) && !"jpeg".equals(extesion)) {
				result.rejectValue("imageFile", null, "JPEGファイルを選択してください");
			}
			try {
				if (imageFile.getBytes().length >= 100000) {
					result.rejectValue("imageFile", null, "100KB未満のJPEGファイルを選択してください");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!result.hasErrors()) {				
				String destPath = application.getRealPath("/img/" + fileName);
				File destFile = new File(destPath);
				try {
					imageFile.transferTo(destFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				item.setImagePath(fileName);
			}
		}
		if (result.hasErrors()) {
			return showInsertItemView(item.getId(), form, model);
		}
		item.setPrice(form.getIntPrice());
		repository.update(item);
		redirect.addFlashAttribute("message", "編集を完了しました。");
		return "redirect:/admin/adminItemList";
	}
}
