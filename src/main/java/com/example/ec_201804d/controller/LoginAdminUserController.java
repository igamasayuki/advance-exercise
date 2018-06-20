package com.example.ec_201804d.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.form.LoginAdminForm;
import com.example.ec_201804d.repository.AdminUserRepoistory;




@Controller
@RequestMapping(value="/adminLogin")
public class LoginAdminUserController {
	@Autowired
	private AdminUserRepoistory adminUserRepository;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public LoginAdminForm setUpLoginForm() {
		return new LoginAdminForm();
	}
	
	@RequestMapping(value="/viewAdminLogin")
	public String viewLogin() {
		return "administerLogin";
	}
	
	@RequestMapping(value="/fromLogintoMenu")
	public String adminLogin(@Validated LoginAdminForm form,
							BindingResult result,
							Model model){
		AdminUser adminUser=adminUserRepository.findByMailAddress(form.getMailAddress());
		if(adminUser==null) {
			result.rejectValue("mailAddress", "", "メールアドレスかパスワードが間違ってます");
		}
		
		if(result.hasErrors()) {
			return "administerLogin";
		}
		session.setAttribute("name",adminUser.getName());
		return "administerTop";
	}
	
}
