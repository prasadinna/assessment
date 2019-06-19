package com.myretail.app.products.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailsController {
	@RequestMapping("/greeting")
	public String hello() {
		return "Hello!";
	}
}
