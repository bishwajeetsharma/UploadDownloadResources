package com.biswsha.ssl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class Home {

	@Autowired
	ConsumeRestApi consumer;
	
	@Autowired
	private FileService fileService;
	
	
	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		return consumer.consumeApi();
	}
	
	@RequestMapping("/helloserver")
	@ResponseBody
	public String helloServer() {
		return "Hello from server on port 8080";
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
			return fileService.saveFile(new FileDB(StringUtils.cleanPath(uploadedfile.getOriginalFilename()),new Date(),uploadedfile.getBytes()));
			}
			catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("File not uploaded",HttpStatus.EXPECTATION_FAILED);
			}
		
		}
		return new ResponseEntity<String>("File not uploaded",HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/downloadfile")
	public String downloadFile(Model model) {
		
		model.addAttribute("filenames",fileService.getFileNames());
		model.addAttribute("heading", "This is a heading!");
		return "downloadFile";
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/download")
	public void download(@RequestParam("fileid") int id, HttpServletResponse response) throws IOException {
		
		FileDB file=fileService.getFileById(id);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
		ServletOutputStream stream= response.getOutputStream();
		stream.write(file.getFile());
		stream.close();
	}
}








