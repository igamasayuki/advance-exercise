package com.example.ec_201804d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
public class MailSendController {

	@Autowired
	private MailSender mailSender;

	@Async
	public void sendOrderMail(String mailAddress) {
		SimpleMailMessage orderMessage = new SimpleMailMessage();
		orderMessage.setTo(mailAddress);
		orderMessage.setText("メール内容");
		mailSender.send(orderMessage);
	}
}
