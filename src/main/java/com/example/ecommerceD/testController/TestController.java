package com.example.ecommerceD.testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
	
	@RequestMapping("/test")
	public String tekito() {
		return "test";
	}
}
