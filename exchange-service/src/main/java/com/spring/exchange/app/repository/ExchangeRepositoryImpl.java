package com.spring.exchange.app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.exchange.app.bean.CurrencyExchangeInfo;

@Repository
public class ExchangeRepositoryImpl implements ExchangeRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String INSERT_QUERY = "INSERT INTO EXCHANGE_RATES (C_FROM, C_TO, C_FACTOR, C_DATE) VALUES (?,?,?,?)";
	
	private final String TRUNCATE_QUERY = "TRUNCATE TABLE EXCHANGE_RATES";
	
	private final String SELECT_QUERY_WITH_CONDITIONS = "SELECT * FROM EXCHANGE_RATES WHERE C_DATE BETWEEN ? AND ? ";
	
	private final String SELECT_QUERY = "SELECT * FROM EXCHANGE_RATES";
	
	@Override
	public int saveCurrencyExchangeInfo(String from,String to, BigDecimal rate, String date) {
		return jdbcTemplate.update(INSERT_QUERY,new Object[] {from,to,rate,date});
	}

	@Override
	public int truncateCurrencyExchangeInfo() {
		return jdbcTemplate.update(TRUNCATE_QUERY);
	}

	@Override
	public List<CurrencyExchangeInfo> getCurrencyInfo(String startDate, String endDate) {
		List<CurrencyExchangeInfo> currencyExchangeInfo = null;
		String selectQuery = (startDate == null && endDate == null) ? SELECT_QUERY : SELECT_QUERY_WITH_CONDITIONS;
		currencyExchangeInfo = jdbcTemplate.query(selectQuery,
				new Object[] { (startDate != null ? startDate : endDate), (endDate != null && !endDate.trim().isEmpty()) ? endDate : startDate },
				new ExchangeServiceRowMapper());
		return currencyExchangeInfo;
	}

	
}
