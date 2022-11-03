package com.izibiz.springboot.stockrestapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stockrestapi")
public class StockProperties {

	private boolean displayStocks = false;

	public boolean isDisplayStocks() {
		return displayStocks;
	}

	public void setDisplayStocks(boolean displayStocks) {
		this.displayStocks = displayStocks;
	}

}
