package com.izibiz.springboot.stockrestapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.izibiz.springboot.stockrestapi.exception.StockNotFoundException;
import com.izibiz.springboot.stockrestapi.model.Stock;

public interface StockService {

	List<Stock> findStocks();

	Stock findStockById(Long id) throws StockNotFoundException;

	Page<Stock> findStocksWithFilter(Map<String, String> filters);

	Stock createStock(Stock stock);

	Stock update(Stock stock);

	void deleteStock(Long id);
	

}
