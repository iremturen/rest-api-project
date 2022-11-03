package com.izibiz.springboot.stockrestapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfiguration {

	@Autowired
	private StockProperties stockProperties;

	@PostConstruct
	public void init() {
		System.out.println("Display stocks:" + stockProperties.isDisplayStocks());
	}
}
