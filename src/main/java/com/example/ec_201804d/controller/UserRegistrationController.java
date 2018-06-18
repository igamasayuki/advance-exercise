package com.example.ec_201804d.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@ModelAttribute
	public UserRegistrationForm setUpUserForm() {
		return new UserRegistrationForm();
	}
	
	@RequestMapping(value="/insert")
	public String insertUser(UserRegistrationForm form, Model model) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userRepository.registerUser(user);
		return "redirect:register/toUserLogin";
	}
	@RequestMapping(value="/toUserRegister")
	public String showUserRagistration() {
		return "userRegistration";
	}
	@RequestMapping(value="/toUserLogin")
	public String showUserLogin() {
		return "userLogin";
	}
}
