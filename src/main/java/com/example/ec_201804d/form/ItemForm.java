package com.example.ec_201804d.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 商品の個数を入力するためのフォームクラスです.
 *
 * @author reo.yasukawa
 *
 */
public class ItemForm {

	/** 個数 */
	@NotBlank(message = "カートに入れる個数を入力してください")
	@Pattern(regexp = "^[1-9][0-9]*+$", message = "正しい個数を入力してください")
	private String quantity;
	/** 商品のID */
	private Long id;

	// /** 以下setter,getter */
	// public Integer getQuantity() {
	// return quantity;
	// }
	//
	// public void setQuantity(Integer quantity) {
	// this.quantity = quantity;
	// }
	public Integer getIntQuantity() {
		return Integer.parseInt(quantity);
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
