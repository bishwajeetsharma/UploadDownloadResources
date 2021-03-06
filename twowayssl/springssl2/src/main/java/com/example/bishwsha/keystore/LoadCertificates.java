package com.example.bishwsha.keystore;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class LoadCertificates {

	@Autowired 
	private KeyStoreUtils keystoreutils;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public String greeting() throws NullPointerException{
		
		String msg="default msg";
		try {
			
			String url="https://localhost:8080/helloserver";
			String getResourceUrl="https://cat-fact.herokuapp.com/facts/";
			if(restTemplate.equals(null)) 
				msg="resttemplate is null";
			HttpEntity<String>entity=new HttpEntity<String>(new HttpHeaders());
			
			ResponseEntity<String>response=restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
			if(response.equals(null)) 
				msg="response is null";
			else 
				msg="response not null, response body: "+response.getBody()+" status code:"+response.getStatusCodeValue();
			
		}
		
		catch(Exception ex){
			System.out.println(ex.getClass()+" "+ex.getMessage());
			msg= "An exception occured during https connection to server at port 8080:"+ex.getMessage();
		}
		return msg;
	}
	
	
	/*
	 * public void loadCertificate() throws FileNotFoundException{
	 * 
	 * HttpHeaders httpheader = new HttpHeaders();
	 * httpheader.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	 * HttpEntity<String>httpentity=new HttpEntity<String>(httpheader);
	 * ResponseEntity<byte[]>response=restTemplate.exchange(
	 * "https://localhost:8080/download?fileid=1",HttpMethod.GET,httpentity,byte[].
	 * class); if(response.getStatusCode()==HttpStatus.OK) { KeyStore
	 * kstr=keystoreutils.createKeyStore(); InputStream in= new
	 * ByteArrayInputStream(response.getBody()); keystoreutils.loadKeyStore(kstr,
	 * in, "password_1"); File file = new File(
	 * "/Users/bishwsha/Documents/ssl-training/UploadDownloadResources/springssl2/newkeystore.p12"
	 * ); OutputStream out = new FileOutputStream(file);
	 * keystoreutils.storeKeyStore(kstr, out, "password_1"); }
	 * 
	 * }
	 */
}
