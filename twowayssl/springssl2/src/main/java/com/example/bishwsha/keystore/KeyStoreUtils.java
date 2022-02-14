package com.example.bishwsha.keystore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeyStoreUtils {
	
	public KeyStore createKeyStore() {
		
		KeyStore keystore=null;
		try {
			keystore=KeyStore.getInstance("PKCS12");
		}
		catch(KeyStoreException ex) {
			
			System.out.println("Keystore exception occured: "+ex.getMessage());
		}
		return keystore;
		
	}
	
	public void loadKeyStore(KeyStore keystore,InputStream stream,String password) {
		
		try {
			keystore.load(stream, password.toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occured : "+e.getClass());
		}
	}
	
	
	public void storeKeyStore(KeyStore keystore,OutputStream stream,String password) {
		try {
			keystore.store(stream, password.toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occured : "+e.getClass());
		}
	}
	
	
	
}
