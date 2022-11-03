package com.izibiz.springboot.stockrestapi.web;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.izibiz.springboot.stockrestapi.exception.StockNotFoundException;
import com.izibiz.springboot.stockrestapi.model.Stock;
import com.izibiz.springboot.stockrestapi.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest")
@Slf4j
public class StockRestController {

	@Autowired
	private StockService stockService;

	
	@GetMapping("/stocks")
	public ResponseEntity<Object> getStocks() {
		List<Stock> stocks = stockService.findStocks();
		return ResponseEntity.ok(stocks);
	}

	@GetMapping("/stock/{id}")
	public ResponseEntity<Object> getStock(@PathVariable("id") Long id) {
		try {
			Stock stock = stockService.findStockById(id);
			return ResponseEntity.ok(stock);
		} catch (StockNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/stock")
	public ResponseEntity<Object> getStockWithFilter(@RequestParam Map<String, String> filters) {
		
		Page<Stock> stocks = stockService.findStocksWithFilter(filters);
		return ResponseEntity.ok(stocks);

	}

	@PostMapping("/stock")
	public ResponseEntity<URI> createOwner(@RequestBody Stock stock) {
		try {
			stockService.createStock(stock);
			Long id = stock.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/stock/{id}")
	public ResponseEntity<?> deleteStock(@PathVariable("id") Long id) {
		try {
			stockService.deleteStock(id);
			return ResponseEntity.ok().build();
		} catch (StockNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/stock/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Stock stockRequest) {
		try {

			stockService.update(stockRequest);
			return ResponseEntity.ok().build();
		} catch (StockNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

}
