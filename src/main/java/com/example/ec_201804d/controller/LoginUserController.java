package com.example.ec_201804d.controller;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.UserLoginForm;

/**
 * ユーザのログイン処理を扱うコントローラ.
 * @author hibiki.ono
 *
 */
@Controller
@RequestMapping(value="/user")
@SessionAttributes(types = {User.class})
public class LoginUserController {
	

	@ModelAttribute
	public UserLoginForm setUpUserLoginForm() {
		return new UserLoginForm();
	}
	
	@RequestMapping(value="/login")
	public String showUserLogin(@Validated UserLoginForm form, BindingResult result, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if(error != null) {
			System.err.println("member: login failed");
			result.rejectValue("email", null, "メールアドレスまたはパスワードが違います");
		}
		
		return "userLogin";
	}
}
