package com.biswsha.ssl.model;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Date uploaded;
	@Lob
	private byte[] file;
	
	public FileDB() {
		
	}
	public FileDB(String name, Date uploaded, byte[] file) {
		
		this.name = name;
		this.uploaded = uploaded;
		this.file = file;
	}
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
	public Date getUploaded() {
		return uploaded;
	}
	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	
	
}
