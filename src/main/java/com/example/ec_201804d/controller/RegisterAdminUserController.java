package com.example.ec_201804d.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.form.RegisterAdminUserForm;
import com.example.ec_201804d.repository.AdminUserRepoistory;




@Controller
@RequestMapping(value="/registerAdmin")
public class RegisterAdminUserController {
	@Autowired
	private AdminUserRepoistory adminUserRepository;
	
	@ModelAttribute
	public RegisterAdminUserForm setUpForm() {
		return new RegisterAdminUserForm();
	}
	
	@RequestMapping(value="/viewRegisterAdmin")
	public String viewRegisterAdmin() {
		return "administerRegistration";
	}
	
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
		//registerAdminUser.setPassword(passwordEncode.encodePassword(form.getPassword());
		adminUserRepository.insert(admin);
		return "redirect:/adminMenu/viewAdminMenu";
	}
}
