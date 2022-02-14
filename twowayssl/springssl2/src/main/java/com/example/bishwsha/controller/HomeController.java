package com.example.bishwsha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bishwsha.keystore.LoadCertificates;

@Controller
@CrossOrigin("*")
public class HomeController {
	
	
	@Autowired
	LoadCertificates lcerts;
	
	
	@ResponseBody
	@RequestMapping("/helloclient")
	public String helloClient() {
		return "Hello from client at port 8090";
	}
	
	@ResponseBody
	@RequestMapping("/hitserver")
	public String hitserver() {
		
		return lcerts.greeting();
	}
}


