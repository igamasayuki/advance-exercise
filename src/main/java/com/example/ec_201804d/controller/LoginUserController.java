package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.form.UserLoginForm;

/**
 * ユーザのログイン処理を扱うコントローラ.
 * @author hibiki.ono
 *
 */
@Controller
@RequestMapping(value="/userlogin")
public class LoginUserController {

	@ModelAttribute
	public UserLoginForm setUpUserLoginForm() {
		return new UserLoginForm();
	}
	
	@RequestMapping(value="/toUserLogin")
	public String showUserLogin() {
		return "userLogin";
	}
}
