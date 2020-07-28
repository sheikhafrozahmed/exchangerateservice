package com.spring.exchange.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;
import com.spring.exchange.app.bean.ExchangeRates;
import com.spring.exchange.app.repository.ExchangeRepository;

@Service
public class ExchangeServiceImpl implements ExchangeService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ExchangeRepository exchangeRepository;

	@Override
	public int storeExchangeRatesInfoInDB(String specificDate, String base, String symbol) {
		ExchangeRates exchangeRates = getExchangeRatesInfo(specificDate, base,symbol);
		return exchangeRepository.saveCurrencyExchangeInfo(base, symbol, 
										exchangeRates.getRates().get(symbol), specificDate);
	}

	@Override
	public ExchangeRates getExchangeRatesInfo(String specificDate, String base,String symbol) {
		String url = "https://api.ratesapi.io/api/" + specificDate ;

		UriComponentsBuilder builder = 
				UriComponentsBuilder.fromUriString(url)
									.queryParam("base", base)
									.queryParam("symbols", symbol);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
		ExchangeRates exchangeRates = restTemplate.exchange(
										builder.toUriString(),
										HttpMethod.GET,entity,ExchangeRates.class).getBody();
				
		
		return exchangeRates;
	}


	@Override
	public int truncateCurrencyExchangeInfo() {
		return exchangeRepository.truncateCurrencyExchangeInfo();
	}

	@Override
	public List<CurrencyExchangeInfo> getCurrencyInfo(String startDate, String endDate) {
		return exchangeRepository.getCurrencyInfo(startDate, endDate);
	}
	
	

}
