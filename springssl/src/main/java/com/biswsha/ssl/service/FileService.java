package com.biswsha.ssl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biswsha.ssl.model.FileDB;
import com.biswsha.ssl.model.FileInfo;
import com.biswsha.ssl.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	FileRepository filerepo;
	
	public ResponseEntity<String> saveFile(FileDB file){
		
		try {
			filerepo.save(file);
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("File not saved successfully", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>("File saved successfully", HttpStatus.OK);
	}
	
	public List<FileInfo> getFileNames(){
		
		return filerepo.getFileInfo();
		
	}
	
	public FileDB getFileById(int id) {
		
		return filerepo.getById(id);
	}
	
	
}
