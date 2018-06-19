package com.example.ec_201804d.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterAdminUserForm {
	@NotEmpty(message="値を入力してください")
	private String name;
	
	@Email( message = "メールアドレスの形式ではありません" )
	@NotEmpty( message = "値を入力してください")
	private String email;
	
	@NotEmpty( message = "値を入力してください")
	private String password;
	
	@NotEmpty( message = "値を入力してください")
	private String checkPassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
	
}
