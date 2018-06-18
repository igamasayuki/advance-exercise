package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.form.RegisterAdminUserForm;


@Controller
@RequestMapping(value="/registerAdmin")
public class RegisterAdminUserController {
	
	@ModelAttribute
	public RegisterAdminUserForm setUpForm() {
		return new RegisterAdminUserForm();
	}
	
	@RequestMapping(value="/viewRegisterAdmin")
	public String viewRegisterAdmin() {
		return "administerRegistration";
	}
}
