package com.example.ec_201804d.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author hibiki.ono
 *
 *         ユーザ登録情報のリクエストパラメータを格納しておくためのフォームクラス.
 */
public class UserRegistrationForm {

	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String conPassword;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "[0-9]+", message = "郵便番号が不正です")
	private String zipCode;
	/** 住所 */
	private String address;
	/** 都道府県市区町村 */
	@NotBlank(message = "住所を入力してください")
	private String address1;
	/** 番地 */
	@NotBlank(message = "住所を入力してください")
	private String address2;
	/** 建物名 */
	private String address3;
	/** 電話番号 */
	private String telephone;
	/** 市外局番 */
	private String telephone1;
	/** 電話番号上4桁 */
	private String telephone2;
	/** 電話番号下4桁 */
	private String telephone3;

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

	public String getConPassword() {
		return conPassword;
	}

	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		address = address1 + address2 + address3;
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		telephone = telephone1 + "-" + telephone2 + "-" + telephone3;
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getTelephone3() {
		return telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

}
