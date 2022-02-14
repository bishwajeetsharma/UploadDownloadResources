package com.biswsha.ssl.model;

import org.springframework.stereotype.Component;

@Component
public class FileInfo {

	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public FileInfo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public FileInfo() {
	}
	
	
	
}
