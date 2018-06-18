package com.example.ec_201804d.domain;

import java.sql.Date;
import java.util.List;

/**
 * @author kakiki
 *注文を表すクラス.
 */
public class Order {
	/**id*/
	long id;
	/**注文番号*/
	String orderNumber;
	/**ユーザーid*/
	long userId;
	/**注文ステータス*/
	Integer status;
	/**注文品物一覧*/
	List<Item> orderItemList;
	/**合計金額*/
	Integer totalPrice;
	/**注文日*/
	Date orderDate;
	/**お届け先名前*/
	String deliveryName;
	/**お届け先Eメールアドレス*/
	String deliveryEmail;
	/**お届け先郵便番号*/
	String deliveryZipCode;
	/**お届け先住所*/
	String deliveryAddress;
	/**お届け先電話番号*/
	String deliveryTel;
	/**ユーザー*/
	User user;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Item> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<Item> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryEmail() {
		return deliveryEmail;
	}
	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	public String getDeliveryZipCode() {
		return deliveryZipCode;
	}
	public void setDeliveryZipCode(String deliveryZipCode) {
		this.deliveryZipCode = deliveryZipCode;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
