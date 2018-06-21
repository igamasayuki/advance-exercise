package com.example.ec_201804d.domain;

/**
 * @author kakiki
 *管理者を表すクラス.
 */
public class AdminUser {
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	/**id*/
	private long id;
	/**名前*/
	private String name;
	/**Eメールアドレス*/
	private String email;
	/** パスワード*/
	private String password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
}
