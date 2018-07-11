package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class ViewAdminMenuController {
	
	@RequestMapping(value="/viewAdminTop")
	public String viewAdminTop() {
		return "administerTop";
	}
}
