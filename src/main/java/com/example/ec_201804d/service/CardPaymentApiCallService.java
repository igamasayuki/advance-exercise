package com.example.ec_201804d.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ec_201804d.domain.PaymentApiDomain;
import com.example.ec_201804d.form.CardDetailInfoForm;

/**
 * カード決済APIを呼び出すサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class CardPaymentApiCallService {

	@Autowired
	private RestTemplate restTemplate;

//	private static final String URL = "http://153.126.174.131:8080/web-api-sample/credit-card/payment";  //伊賀さんが作成
	private static final String URL = "http://172.16.100.36:8080/card-payment-api/usercredit-card/cardpayment"; //自作のWebAPI(デプロイ)

	/**
	 * カード決済WebAPIを呼び出し、レスポンスを返す.
	 * 
	 * @param form クレジットカード情報・注文番号・購入者ID・総計
	 * @return　WebAPIのレスポンス
	 */
	public PaymentApiDomain paymentApiService(CardDetailInfoForm form) {

		return restTemplate.postForObject(URL, form, PaymentApiDomain.class);
	}
}
