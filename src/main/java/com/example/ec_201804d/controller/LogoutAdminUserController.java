package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.form.LoginAdminForm;

@Controller
@RequestMapping(value="/adminLogout")
public class LogoutAdminUserController {
	@ModelAttribute
	public LoginAdminForm setUpLoginForm() {
		return new LoginAdminForm();
	}
	
	@RequestMapping(value="/viewAdminLogout")
	public String adminLogout() {
		return "administerLogin";
	}
}
