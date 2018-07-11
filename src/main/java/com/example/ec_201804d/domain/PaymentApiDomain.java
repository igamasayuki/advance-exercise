package com.example.ec_201804d.domain;

/**
 * WebAPIからのレスポンスを受け取るドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class PaymentApiDomain {

	/** APIレスポンスステータス */
	private String status;
	/** APIレスポンスメッセージ */
	private String message;
	/** エラーコード */
	private String errorCode;
	/** 購入者ID */
	private long user_id;
	/** 決済金額(数字10桁) */
	private Integer amount;
	/** カード番号(数字14桁-16桁) */
	private Integer card_number;
	/** カード有効期限(年)(数字4桁) */
	private Integer card_exp_year;
	/** カード有効期限(月)(数字2桁) */
	private Integer card_exp_month;
	/** セキュリティコード(数字3桁-4桁) */
	private Integer card_cvv;
	/** カード名義人名 */
	private String card_name;
	/** 注文番号 */
	private String oeder_number;

	@Override
	public String toString() {
		return "PaymentApiDomain [status=" + status + ", message=" + message + ", errorCode=" + errorCode + "]";
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

	public long getClientId() {
		return user_id;
	}

	public void setClientId(long clientId) {
		this.user_id = clientId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCardNumber() {
		return card_number;
	}

	public void setCardNumber(Integer cardNumber) {
		this.card_number = cardNumber;
	}

	public Integer getCardExpYear() {
		return card_exp_year;
	}

	public void setCardExpYear(Integer cardExpYear) {
		this.card_exp_year = cardExpYear;
	}

	public Integer getCardExpMonth() {
		return card_exp_month;
	}

	public void setCardExpMonth(Integer cardExpMonth) {
		this.card_exp_month = cardExpMonth;
	}

	public Integer getCardCvv() {
		return card_cvv;
	}

	public void setCardCvv(Integer cardCvv) {
		this.card_cvv = cardCvv;
	}

	public String getNominee() {
		return card_name;
	}

	public void setNominee(String nominee) {
		this.card_name = nominee;
	}

	public String getOederNumber() {
		return oeder_number;
	}

	public void setOederNumber(String oederNumber) {
		this.oeder_number = oederNumber;
	}

}
