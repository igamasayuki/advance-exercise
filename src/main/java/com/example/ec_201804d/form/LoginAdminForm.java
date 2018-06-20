package com.example.ec_201804d.form;

public class LoginAdminForm {
	/**メールアドレス*/
	String email;
	/**パスワード*/
	String password;
	
	public String getMailAddress() {
		return email;
	}
	
	public void setMailAddress(String mailAddress) {
		this.email = mailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
