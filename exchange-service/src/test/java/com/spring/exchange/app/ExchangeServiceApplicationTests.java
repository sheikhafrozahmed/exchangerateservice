package com.spring.exchange.app;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.spring.exchange.app.bean.RequestInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ExchangeServiceApplicationTests {
	
	@LocalServerPort
	public int randonServerPort;
	
	 @Test
	public void testGetCurrencyExchangeInfo() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randonServerPort + "/exchange/2019-10-01?endDate=2019-11-01";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	
	}
	 
	 @Test
	public void loadCurrencyExchangeInfoByBase() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randonServerPort + "/exchange/";
		URI uri = new URI(baseUrl);
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setBase("USD");
		requestInfo.setSymbol("HKD");
		requestInfo.setDate("2020-06-01");
		  
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<RequestInfo> request = new HttpEntity<>(requestInfo, headers);
        
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	
	}


	@Test
	public void contextLoads() {
	}

}
