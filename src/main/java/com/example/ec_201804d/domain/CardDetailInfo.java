package com.example.ec_201804d.domain;

/**
 * カード決済に使用するカード情報を扱うドメインクラス.
 * 
 * @author hibiki.ono
 *
 */
public class CardDetailInfo {

	/** 購入者ID */
	private long clientId;
	/** 決済金額(数字10桁) */
	private Integer amount;
	/** カード番号(数字14桁-16桁) */
	private Integer cardNumber;
	/** カード有効期限(年)(数字4桁) */
	private Integer cardExpYear;
	/** カード有効期限(月)(数字2桁) */
	private Integer cardExpMonth;
	/** セキュリティコード(数字3桁-4桁) */
	private Integer cardCvv;
	/** カード名義人 */
	private String nominee;
	/** 注文番号 */
	private String orderNumber;
	/** 支払方法ステータス*/
	private Integer paymentMethod;

	@Override
	public String toString() {
		return "CardDetailInfo [clientId=" + clientId + ", amount=" + amount + ", cardNumber=" + cardNumber
				+ ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardCvv=" + cardCvv
				+ ", nominee=" + nominee + ", orderNumber=" + orderNumber + "]";
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public Integer getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public Integer getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(Integer cardCvv) {
		this.cardCvv = cardCvv;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
