package com.example.ec_201804d.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.User;
import com.example.ec_201804d.form.UserRegistrationForm;
import com.example.ec_201804d.repository.UserRepository;

/**
 * @author hibiki.ono
 *
 *利用者登録するコントローラクラス.
 */
@Controller
@RequestMapping(value="/register")
public class UserRegistrationController {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * ユーザフォームの初期化.
	 * @return　インスタンス化
	 */
	@ModelAttribute
	public UserRegistrationForm setUpUserForm() {
		return new UserRegistrationForm();
	}
	
	/**
	 * ユーザ登録.
	 * @param form　リクエストパラメータ
	 * @param model　リクエストスコープ
	 * @return　リダイレクト
	 */
	@RequestMapping(value="/insert")
	public String insertUser(@Validated UserRegistrationForm form, BindingResult result, Model model) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		String password = form.getPassword();
		String conPassword = form.getConPassword();
		if(!password.equals(conPassword)) {
			result.rejectValue("conPassword", null, "パスワードが違います");
		}
		if(result.hasErrors()) {
			return "userRegistration";
		}
		
		userRepository.registerUser(user);
		return "redirect:/userlogin/toUserLogin";
	}
	/**
	 * ユーザ登録画面表示.
	 * @return　ユーザ登録画面
	 */
	@RequestMapping(value="/toUserRegister")
	public String showUserRegistration() {
		return "userRegistration";
	}
}
