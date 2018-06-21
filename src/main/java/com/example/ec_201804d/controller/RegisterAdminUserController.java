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

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.form.RegisterAdminUserForm;
import com.example.ec_201804d.repository.AdminUserRepository;




/**
 * @author kakiki
 *管理者登録のコントローラー.
 */
@Controller
@RequestMapping(value="/admin")
public class RegisterAdminUserController {
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public RegisterAdminUserForm setUpForm() {
		return new RegisterAdminUserForm();
	}
	
	/**
	 * 管理者登録画面表示.
	 * @return 管理者登録画面の表示
	 */
	@RequestMapping(value="/viewRegisterAdmin")
	public String viewRegisterAdmin() {
		return "administerRegistration";
	}
	
	/**
	 * 管理者登録実行もしエラー出たら管理者登録画面に戻る.
	 * @param form 登録フォーム
	 * @param result エラー
	 * @param model　モデル
	 * @return
	 */
	@RequestMapping(value="/registerAdminUser")
	public String registerAdminUser(@Validated RegisterAdminUserForm form,
									BindingResult result,
									Model model) {
		
		AdminUser admin=new AdminUser();
		
		
		if(!form.getPassword().equals(form.getCheckPassword())){
			result.rejectValue("checkPassword", "", "パスワードを確認してください");
		}
		
		AdminUser adminUser = adminUserRepository.findByMailAddress(form.getEmail());
		if(adminUser != null){
			result.rejectValue("email", "", "すでにメールアドレスが登録されています");
		}
		
		if(result.hasErrors()) {
			return "administerRegistration";
		}
		
		BeanUtils.copyProperties(form,admin);
		admin.setPassword(passwordEncoder.encode(form.getPassword()));
		adminUserRepository.insert(admin);
		return "redirect:/adminMenu/viewAdminMenu";
	}
}
