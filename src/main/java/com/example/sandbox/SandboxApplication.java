package com.example.sandbox;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SandboxApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SandboxApplication.class, args);
		RestTemplate template = ctx.getBean(RestTemplate.class);
	}

	public static void performPingBasicAuthEndPoint(RestTemplate template) {
		String url = "http://localhost:8080/run";

		List<Long> orderIds = Arrays.asList(new Long[] { 100L, 101L });


		ExecutorService executor = Executors.newFixedThreadPool(3);
		List<Callable<String>> callables = new ArrayList<>();
		
		for(Long orderId: orderIds) {
			
			callables.add( () -> {
				String jsonRequest = String.format("{\"orderId\":\"%s\"}", orderId);
				HttpEntity<String> request = new HttpEntity<String>(jsonRequest, getHeaders());
				template.postForObject(url, request, String.class);
				return "done";
			});
		}
		
		try {
			List<Future<String>> futures = executor.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String auth = "FIL_REST:password";
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String base64Creds = new String(encodedAuth);
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}

}
