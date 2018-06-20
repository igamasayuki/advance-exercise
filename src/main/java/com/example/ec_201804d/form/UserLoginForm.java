package com.example.ec_201804d.form;

/**
 * ユーザのログイン情報を扱うフォームクラス.
 * @author hibiki.ono
 *
 */
public class UserLoginForm {

	/**メールアドレス*/
	private String email;
	/**パスワード*/
	private String password;
	
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
}
