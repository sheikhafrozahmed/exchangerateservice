package com.spring.exchange.app.service;

import java.util.List;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;
import com.spring.exchange.app.bean.ExchangeRates;

public interface ExchangeService {
	
	public ExchangeRates getExchangeRatesInfo(String specificDate, String base, String symbol);
	
	public int storeExchangeRatesInfoInDB(String specificDate, String base, String symbol);

	public int truncateCurrencyExchangeInfo();
	
	public List<CurrencyExchangeInfo> getCurrencyInfo(String startDate,String endDate);

}
