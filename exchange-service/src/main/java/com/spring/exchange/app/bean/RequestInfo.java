package com.spring.exchange.app.bean;

import java.time.LocalDate;

public class RequestInfo {

	private String base = "EUR";
	
	private String symbol = "GBP";
	
	private String date = LocalDate.now().toString();

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
