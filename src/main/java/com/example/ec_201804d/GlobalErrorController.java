package com.example.ec_201804d;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * エラーページを表示させるコントローラ.
 * 
 * @author minori.matsuoka
 *
 */
@Controller
@RequestMapping("/500error")
public class GlobalErrorController {

	/**
	 * 500エラー画面に遷移する.
	 * 
	 * @return 500エラー時の画面
	 */
	@RequestMapping("/maintenance")
	public String maintenance() {
		return "error_page/500errorPage";
	}
	
}
