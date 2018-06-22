package com.example.ec_201804d.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.ec_201804d.domain.AdminUser;
import com.example.ec_201804d.form.LoginAdminForm;
import com.example.ec_201804d.repository.AdminUserRepository;




@Controller
@RequestMapping(value="/admin")
@SessionAttributes(types = {AdminUser.class})
public class LoginAdminUserController {
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public LoginAdminForm setUpLoginForm() {
		return new LoginAdminForm();
	}
	
	@RequestMapping(value="/login")
	public String viewLogin() {
		return "administerLogin";
	}
	
	@RequestMapping(value="/fromLogintoMenu")
	public String adminLogin(@Validated LoginAdminForm form,
							BindingResult result,
							@RequestParam(required = false) String error){
		System.err.println("adminLogin error:" + error);
		if(error != null) {
			System.err.println("adminUser: login failed");
			result.rejectValue("email", null, "メールアドレスまたはパスワードが違います");
		}
		
		return "administerTop";
	}
	
}
