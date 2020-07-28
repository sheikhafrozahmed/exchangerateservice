package com.spring.exchange.app.bean;

import java.math.BigDecimal;

public class CurrencyExchangeInfo {
	
	private int id;
	
	private String from;
	
	private String to;
	
	private BigDecimal factor;
	
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeInfo [id=" + id + ", from=" + from + ", to=" + to + ", factor=" + factor + ", date="
				+ date + "]";
	}
	
	
}
