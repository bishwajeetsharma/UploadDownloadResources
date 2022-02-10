package com.biswsha.ssl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biswsha.ssl.model.FileDB;
import com.biswsha.ssl.service.FileService;

@Controller
public class Home {

	@Autowired
	ConsumeRestApi consumer;
	
	@Autowired
	private FileService fileService;
	
	
	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		return consumer.consumeApi();
	}
	
	@RequestMapping("/upload")
	public String uploadFile() {
		
		return "uploadFile";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/uploadfile")
	public  ResponseEntity<String> getFile(@RequestParam("fileuploaded") MultipartFile uploadedfile) {
		
		if(!uploadedfile.isEmpty()) {
		
			try {
				System.out.println(uploadedfile.getContentType());
			return fileService.saveFile(new FileDB(uploadedfile.getName(),new Date(),uploadedfile.getBytes()));
			}
			catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("File not uploaded",HttpStatus.EXPECTATION_FAILED);
			}
		
		}
		return new ResponseEntity<String>("File not uploaded",HttpStatus.BAD_REQUEST);
	}
}

