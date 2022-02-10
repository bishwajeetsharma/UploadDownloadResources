package com.biswsha.ssl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumeRestApi {

	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<String> consumeApi() {
		
		String getResourceUrl="https://cat-fact.herokuapp.com/facts/";
		ResponseEntity<String>response=restTemplate.getForEntity(getResourceUrl,String.class);
		return response;
	}
}
