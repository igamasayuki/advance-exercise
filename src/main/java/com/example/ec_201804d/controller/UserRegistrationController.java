package com.example.ec_201804d.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 *         利用者登録するコントローラクラス.
 */
@Controller
@RequestMapping(value = "/user")
public class UserRegistrationController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * ユーザフォームの初期化.
	 * 
	 * @return インスタンス化
	 */
	@ModelAttribute
	public UserRegistrationForm setUpUserForm() {
		return new UserRegistrationForm();
	}

	/**
	 * ユーザ登録.
	 * 
	 * @param form
	 *            リクエストパラメータ
	 * @param model
	 *            リクエストスコープ
	 * @return リダイレクト
	 */
	@RequestMapping(value = "/register")
	public String insertUser(@Validated UserRegistrationForm form, BindingResult result, Model model) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		String mailAddress = form.getEmail();
		User userEmail = (userRepository.findByEmailAddress(mailAddress));
		if(userEmail != null) {
			result.rejectValue("email", null, "そのメールアドレスは既に使われています");
		}

		String password = form.getPassword();
		String conPassword = form.getConPassword();
		if (!password.equals(conPassword)) {
			result.rejectValue("conPassword", null, "パスワードが違います");
		}

		String telephone1 = form.getTelephone1();
		String telephone2 = form.getTelephone2();
		String telephone3 = form.getTelephone3();
		boolean errorTelephoneEmpty = (telephone1.equals("") || telephone2.equals("") || telephone3.equals(""));
		boolean errorTelephoneNumber = (telephone1.length() >= 5 || telephone2.length() >= 5
				|| telephone3.length() >= 5);

		if (errorTelephoneEmpty) {
			result.rejectValue("telephone", null, "電話番号を入力してください");
		} else if (errorTelephoneNumber) {
			result.rejectValue("telephone", null, "電話番号はXXXX-XXXX-XXXX形式にしてください");
		}
		try {
			Integer.parseInt(telephone1);
			Integer.parseInt(telephone2);
			Integer.parseInt(telephone3);
		} catch (NumberFormatException ex) {
			result.rejectValue("telephone", null, "電話番号が不正です");
		}
		if (result.hasErrors()) {
			return "userRegistration";
		}
		
		password = passwordEncoder.encode(form.getPassword());
		String address = form.getAddress();
		String telephone = form.getTelephone();
		user.setPassword(password);
		user.setAddress(address);
		user.setTelephone(telephone);

		userRepository.registerUser(user);
		return "redirect:/user/login";
	}

	/**
	 * ユーザ登録画面表示.
	 * 
	 * @return ユーザ登録画面
	 */
	@RequestMapping(value = "/toUserRegister")
	public String showUserRegistration() {
		return "userRegistration";
	}
}
