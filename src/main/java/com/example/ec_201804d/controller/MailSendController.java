package com.example.ec_201804d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import com.example.ec_201804d.domain.Info;

/**
 * 注文確定メール送信機能を扱うコントローラクラス.
 * @author hibiki.ono
 *
 */
@Controller
public class MailSendController {

	@Autowired
	private MailSender mailSender;

	/**
	 * 注文確定メールを送信するメソッド.
	 * @param mailAddress 利用者のメールアドレス
	 * @param orderList　利用者が注文した商品リスト
	 */
	@Async
	public void sendOrderMail(String mailAddress, List<Info> orderList) {

		String itemName;
		int itemPrice;
		int itemQuantity;
		String sumItemInfo = "";
		int sumPrice = 0;
	    double totalPrice = 0;
		for (int i = 0; i < orderList.size(); i++) {
			itemName = orderList.get(i).getName();
			itemPrice = orderList.get(i).getPrice() * orderList.get(i).getQuantity();
			itemQuantity = orderList.get(i).getQuantity();
			sumPrice += itemPrice;
			sumItemInfo += itemName + " " +String.valueOf(itemQuantity) + "個" + " " + String.valueOf(itemPrice) + "円(税抜き)\n";
		}
		totalPrice = ((sumPrice*1.08) + 500);
		String orderMail = "ご注文ありがとうございます。\nお客様がご注文された商品は以下の通りでございます。\n";
		String body = "==============================\n";
		String body2 = "-------------------------------------------\n";
		String mailText = orderMail + body + sumItemInfo + body2 + "合計：" + (int)totalPrice + "円(税込み・送料込み)\n" + body;

		SimpleMailMessage orderMessage = new SimpleMailMessage();
		orderMessage.setTo(mailAddress);
		orderMessage.setSubject("ご注文が確定いたしました");
		orderMessage.setText(mailText);
		mailSender.send(orderMessage);
	}
}
