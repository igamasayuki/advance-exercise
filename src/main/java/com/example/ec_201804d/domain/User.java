package com.example.ec_201804d.domain;

/**
 * @author kakiki
 *ユーザーを現すクラス.
 */
public class User {
	/**id*/
	long id;
	/**名前*/
	String name;
	/**Eメールアドレス*/
	String email;
	/** パスワード*/
	String password;
	/** 郵便番号*/
	String zipCode;
	/**住所*/
	String address;
	/** 電話番号*/
	String telephone;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
