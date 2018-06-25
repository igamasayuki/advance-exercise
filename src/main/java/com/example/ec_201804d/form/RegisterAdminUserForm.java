package com.example.ec_201804d.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author kakiki
 *管理者登録フォーム.
 */
public class RegisterAdminUserForm {
	/**名前*/
	@NotEmpty(message="名前が未入力です")
	private String name;
	
	/**eメール*/
	@Email( message = "メールアドレスの形式ではありません" )
	@NotEmpty( message = "メールアドレスが未入力です")
	private String email;
	
	/**パスワード*/
	@NotEmpty( message = "パスワードが未入力です")
	private String password;
	
	/**確認用パスワード*/
	@NotEmpty( message = "確認パスワードが未入力です")
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
