package com.example.ec_201804d.domain;

/**
 * キャンセルAPIのレスポンス情報を扱うドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class ResponseCancelAPIDomain {

	private String status;
	private String message;
	private String errorCode;
	private String order_number;

	@Override
	public String toString() {
		return "ResponseCancelAPIDomain [status=" + status + ", message=" + message + ", errorCode=" + errorCode
				+ ", order_number=" + order_number + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
}
