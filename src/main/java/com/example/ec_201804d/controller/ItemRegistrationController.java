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

@Controller
@Transactional
@RequestMapping(value="/item_registration")
public class ItemRegistrationController {
	@Autowired
	private ItemRepository repository;
	@Autowired
	private ServletContext application;

	@ModelAttribute
	public static ItemRegistrationForm setUpForm() {
		return new ItemRegistrationForm();
	}
	
	@RequestMapping(value="/show_view")
	public String showInsertItemView(Model model) {
		return "insertItem";
	}
	
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
