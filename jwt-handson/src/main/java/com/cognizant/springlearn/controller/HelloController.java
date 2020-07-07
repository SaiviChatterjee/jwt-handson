package com.cognizant.springlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
}
