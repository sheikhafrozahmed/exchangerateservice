package com.spring.exchange.app.repository;

import java.math.BigDecimal;
import java.util.List;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;

public interface ExchangeRepository {
	
	public int saveCurrencyExchangeInfo(String from,String to, BigDecimal rate, String date);
	
	public int truncateCurrencyExchangeInfo();
	
	public List<CurrencyExchangeInfo> getCurrencyInfo(String startDate,String endDate);

}
