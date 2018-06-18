package com.example.ec_201804d.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.Item;
import com.example.ec_201804d.form.ItemRegistrationForm;
import com.example.ec_201804d.repository.ItemRepository;

@Controller
@Transactional
@RequestMapping(value="/item_registration")
public class ItemRegistrationController {
	@Autowired
	private ItemRepository repository;

	@ModelAttribute
	public static ItemRegistrationForm setUpForm() {
		return new ItemRegistrationForm();
	}
	
	@RequestMapping(value="/show_view")
	public String showInsertItemView() {
		return "insertItem";
	}
	
	@RequestMapping(value="/register")
	public String registerItem(@Validated ItemRegistrationForm form, BindingResult result) {
		if (result.hasErrors()) {
			return showInsertItemView();
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		repository.insert(item);
		return "redirect:/itemList/viewItemList";
	}
}
