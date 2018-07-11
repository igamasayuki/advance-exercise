package com.example.ec_201804d.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_201804d.domain.PaymentApiDomain;
import com.example.ec_201804d.form.CardDetailInfoForm;
import com.example.ec_201804d.service.CardPaymentApiCallService;

/**
 * カード決済のAPIを呼び出すコントローラクラス.
 * 
 * @author hibiki.ono
 *
 */
@Transactional
@Controller
@RequestMapping(value = "/userPaymentApi")
public class CardPaymentApiCallController {

	@Autowired
	private CardPaymentApiCallService paymentApiCallService;
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public CardDetailInfoForm setUpCardDetailInfoForm() {
		return new CardDetailInfoForm();
	}

	/**
	 * カード決済WebAPI呼び出し.
	 * 
	 * @param cardDetailInfoForm　クレジットカード情報と支払方法ステータスを格納したリクエストパラメータ　
	 * @param model リクエストスコープ
	 * @return　決済完了画面　または　WebAPIレスポンスに応じて処理を行うメソッド
	 */
	@RequestMapping(value = "/callPaymentApi")
	public String callPaymentApi(CardDetailInfoForm cardDetailInfoForm, Model model) {
		if (!cardDetailInfoForm.getPayMethod().equals("1")) {
			return "redirect:/userPayment/closeOut";
		}
		
		PaymentApiDomain paymentApiDomain = paymentApiCallService.paymentApiService(cardDetailInfoForm);
		session.setAttribute("paymentApiResponse", paymentApiDomain.getStatus());
		return "redirect:/userPayment/paymentApiResponse";
	}
}
