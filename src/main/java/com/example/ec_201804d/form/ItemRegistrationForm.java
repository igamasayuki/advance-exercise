package com.example.ec_201804d.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品登録フォーム
 *
 * @author daiki.fujioka
 *
 */
public class ItemRegistrationForm {

	/** 商品名 */
	@NotBlank(message = "商品名を入力してください")
	private String name;
	/** 価格 */
	@NotBlank(message = "価格を入力してください")
	@Max(value=1000000, message="1~1,000,000の数字を入力してください")
	@Pattern(regexp="^[1-9][0-9]*$", message="整数値を入力してください")
	private String price;
	/** 商品説明 */
	@NotBlank(message = "説明を入力してください")
	private String description;
	/** 商品画像ファイル */
	private MultipartFile imageFile;
	/** 削除フラグ */
	private boolean deleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}
	
	public Integer getIntPrice() {
		return Integer.parseInt(price);
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
