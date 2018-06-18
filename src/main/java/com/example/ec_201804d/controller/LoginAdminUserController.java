package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/k")
public class LoginAdminUserController {
	
	@RequestMapping(value="/adminLogin")
	public String viewLogin() {
		return "admin/administerLogin";
	}
}
