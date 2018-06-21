package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
public class ShowShoppingCartController {
	
	@RequestMapping("/showShoppingCart")
	public String showShoppingCart() {
		return "viewShoppingCart";
	}
}
