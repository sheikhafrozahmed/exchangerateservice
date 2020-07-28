package com.spring.exchange.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;

public class ExchangeServiceRowMapper implements RowMapper<CurrencyExchangeInfo>{
	
	public CurrencyExchangeInfo mapRow(ResultSet resultSet, int i) throws SQLException {
		CurrencyExchangeInfo currencyExchangeInfo = new CurrencyExchangeInfo();
		currencyExchangeInfo.setId(resultSet.getInt("ID"));
		currencyExchangeInfo.setFrom(resultSet.getString("C_FROM"));
		currencyExchangeInfo.setTo(resultSet.getString("C_TO"));
		currencyExchangeInfo.setFactor(resultSet.getBigDecimal("C_FACTOR"));
		currencyExchangeInfo.setDate(resultSet.getString("C_DATE"));
		return currencyExchangeInfo;
	}

}
