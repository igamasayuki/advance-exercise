package com.example.ec_201804d.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

public class ItemRegistrationForm {

	@NotBlank(message="商品名を入力してください")
	private String name;
	@NotNull(message="価格を入力してください")
	private Integer price;
	@NotBlank(message="説明を入力してください")
	private String description;
	@NotBlank(message="表示する商品画像を選択してください")
	private String imagePath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
