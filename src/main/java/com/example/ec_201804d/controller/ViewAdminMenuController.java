package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/adminMenu")
public class ViewAdminMenuController {
	
	@RequestMapping(value="/viewAdminMenu")
	public String viewAdminMenu() {
		return "administerMenu";
	}
}
