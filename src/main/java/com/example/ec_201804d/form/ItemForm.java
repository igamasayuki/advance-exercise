package com.example.ec_201804d.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 商品の個数を入力するためのフォームクラスです.
 *
 * @author reo.yasukawa
 *
 */
public class ItemForm {

	/** 個数 */
//	@NotNull(message="個数を入力してください")
//	@Pattern(regexp = "^[1-9][0-9]*+$",message="自然数で入力してください")
	private Integer quantity;
	/** 商品のID */
	private Long itemId;

	/** 以下setter,getter */
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}