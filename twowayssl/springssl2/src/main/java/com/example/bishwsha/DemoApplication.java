package com.example.bishwsha;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.function.Supplier;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLServerSocketFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.example.bishwsha.keystore.LoadCertificates;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("application started!");
		

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {
		
		KeyStore keystore=KeyStore.getInstance("PKCS12");
		KeyStore truststore=KeyStore.getInstance("PKCS12");
		ClassPathResource trustresource=new ClassPathResource("truststore/clienttruststore.p12");
		ClassPathResource keyresource=new ClassPathResource("keystore/clientkeystore.p12");
		keystore.load(keyresource.getInputStream(),"password".toCharArray());
		truststore.load(trustresource.getInputStream(), "password".toCharArray());
		
		//SSLConnectionSocketFactory socketfactory= new SSLConnectionSocketFactory(new SSLContextBuilder().loadKeyMaterial(keystore,"password".toCharArray()).loadTrustMaterial(truststore).build(),NoopHostnameVerifier.INSTANCE);
		
		//SSLContext sslcontext= SSLContexts.custom().loadKeyMaterial(keystore,"password".toCharArray()).loadTrustMaterial(truststore).build();
		//HttpClient httpclient=HttpClients.custom().setSSLSocketFactory(socketfactory).build();
		
		
		SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(keystore, "password".toCharArray()).loadTrustMaterial(truststore, null).build();
		HttpClient httpclient=HttpClients.custom().setSSLContext(sslContext).build();
		
		HttpComponentsClientHttpRequestFactory httprequestfactory= new HttpComponentsClientHttpRequestFactory(httpclient);
		return builder.requestFactory(()->httprequestfactory).build();
		
		
		/*
		 * RestTemplate restTemplate=new RestTemplate();
		 * restTemplate.setRequestFactory(new
		 * HttpComponentsClientHttpRequestFactory(httpclient)); return restTemplate;
		 */
	}

}
