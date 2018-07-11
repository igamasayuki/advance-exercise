package com.example.ec_201804d.domain;

/**
 * 支払方法を扱うドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class PaymentMethod {

	private Integer paymentMethod;

	@Override
	public String toString() {
		return "PaymentMethod [paymentMethod=" + paymentMethod + "]";
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
