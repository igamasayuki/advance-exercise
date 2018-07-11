package com.example.ec_201804d.domain;

public class CardDetailInfoDomain {
	

	/** 購入者ID */
	private long user_id;
	/** 購入金額 */
	private String amount;
	/** カード番号 */
	private String card_number;
	/** カード有効期限(年)4桁 */
	private String card_exp_year;
	/** カード有効期限(月)2桁 */
	private String card_exp_month;
	/** カード名義人 */
	private String card_name;
	/** セキュリティコード */
	private String card_cvv;
	/** 注文番号 */
	private String order_number;

	@Override
	public String toString() {
		return "CardDetailInfoDomain [user_id=" + user_id + ", amount=" + amount + ", card_number=" + card_number
				+ ", card_exp_year=" + card_exp_year + ", card_exp_month=" + card_exp_month + ", card_name=" + card_name
				+ ", card_cvv=" + card_cvv + ", order_number=" + order_number + "]";
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
}
