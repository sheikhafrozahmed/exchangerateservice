package com.spring.exchange.app.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;
import com.spring.exchange.app.bean.RequestInfo;
import com.spring.exchange.app.helper.Constants;
import com.spring.exchange.app.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeServiceController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	private List<String> symbols = Arrays.asList(Constants.CURRENCY_GBP,Constants.CURRENCY_USD,Constants.CURRENCY_HKD);

	
	@PostMapping("/")
	public ResponseEntity<String> loadCurrencyExchangeInfoByBase(@RequestBody RequestInfo requestInfo) {	
		LocalDate localDate = LocalDate.parse(requestInfo.getDate()).withDayOfMonth(1);
		if(symbols.contains(requestInfo.getSymbol())) {
			exchangeService.truncateCurrencyExchangeInfo();
			int count = 0;
			for (int i = 0; i < 12; i++) {
				count = exchangeService.storeExchangeRatesInfoInDB(localDate.minusMonths(i).toString(), requestInfo.getBase(), requestInfo.getSymbol()) + i;			 
			}
			String message =  count > 0 ? Constants.SUCCESS_MESSAGE : Constants.FAILURE_MESSAGE;
			return ResponseEntity.ok(message);
		} else {
			return ResponseEntity.ok("Exchange Service supports only GBP/USD/HKD");
		}
	}
	
	@GetMapping("/{startDate}")
	public List<CurrencyExchangeInfo> getCurrencyExchangeInfo(@PathVariable String startDate, 
				@RequestParam(value = "endDate", required = false) String endDate) {
		return exchangeService.getCurrencyInfo(startDate, endDate);
	}
	
	
}
