package com.example.ec_201804d.domain;

public class Info {
	String name;
	 Integer price;
	 Integer quantity;
	 Integer totalPrice;
	 public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
	  return name;
	 }

}
