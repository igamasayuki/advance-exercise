package com.example.ec_201804d.testController;

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
