package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.form.LoginForm;

@Controller
@RequestMapping(value="/k")
public class LoginAdminUserController {
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@RequestMapping(value="/viewAdminLogin")
	public String viewLogin() {
		return "administerLogin";
	}
	
	@RequestMapping(value="/AdminLogin")
	public String adminLogin(){
		return "administerMenu";
	}
	
}
