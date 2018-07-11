package com.example.ec_201804d.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.form.LoginAdminForm;

/**
 * 管理者のログイン処理を行うコントローラ.
 *
 * @author daiki.fujioka
 *
 */
@Controller
@RequestMapping(value = "/adminuserlogin")
@SessionAttributes(types = { AdminUser.class })
public class LoginAdminUserController {
	/**
	 * 管理者のログインフォームの初期化.
	 * 
	 * @return 管理者のログイン入力フォーム
	 */
	@ModelAttribute
	public LoginAdminForm setUpLoginForm() {
		return new LoginAdminForm();
	}

	/**
	 * 管理者のログイン画面を表示する.
	 * 
	 * @param form
	 *            管理者のログイン入力フォーム
	 * @param result
	 *            入力チェックの結果
	 * @param error
	 *            入力チェックのエラー内容
	 * @return 管理者のログイン画面
	 */
	@RequestMapping(value = "/index")
	public String viewLogin(@Validated LoginAdminForm form, BindingResult result,
			@RequestParam(required = false) String error) {
		if (error != null) {
			System.err.println("adminUser: login failed");
			result.rejectValue("email", null, "メールアドレスまたはパスワードが違います");
		}

		return "administerLogin";
	}

	/**
	 * 管理者がログインする.
	 * 
	 * @return 管理者のトップ画面
	 */
	@RequestMapping(value = "/fromLogintoMenu")
	public String adminLogin() {
		return "administerTop";
	}

}