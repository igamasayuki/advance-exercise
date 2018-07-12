package com.example.ec_201804d.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ec_201804d.domain.ResponseCancelAPIDomain;
import com.example.ec_201804d.form.OrderNumberForm;

/**
 * キャンセル処理APIを呼び出すサービスクラス.
 * 
 * @author hibiki.ono
 *
 */
@Service
public class CallCancelApiService {

	@Autowired
	private RestTemplate restTemplate;
	
	// 社内サーバで動いているWEB-APIのURL
	private static final String URL = "http://172.16.0.13:8080/web-api-sample/credit-card/cancel";

	/**
	 * キャンセルWebAPIを呼び出してレスポンスを返す.
	 * 
	 * @param form 注文番号
	 * @return　WebAPIのレスポンス
	 */
	public ResponseCancelAPIDomain cancelApiService(OrderNumberForm form) {

		return restTemplate.postForObject(URL, form, ResponseCancelAPIDomain.class);
	}
}
