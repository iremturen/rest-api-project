package com.izibiz.springboot.stockrestapi.web;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.izibiz.springboot.stockrestapi.model.Stock;

public class StockRestControllerTests {

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
	}

	@Test
	public void testGetStockById() {
		ResponseEntity<Stock> response = restTemplate.getForEntity("http://localhost:8080/rest/stocks/1", Stock.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getBrand(), Matchers.equalTo("S"));
	}

	@Test
	public void testGetStocks() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8085/rest/stocks", List.class);
		List<Map<String, String>> body = response.getBody();

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		List<String> id = body.stream().map(e -> e.get("id")).collect(Collectors.toList());
		MatcherAssert.assertThat(id, Matchers.containsInAnyOrder(22, 33, 34, 35, 36, 37));
	}

	@Test
	public void testCreateStock() {
		Stock stock = new Stock();

		stock.setEmail("tttt@gmail.com");
		stock.setBrand("XXX");

		RestTemplate restTemplate = new RestTemplate();
		URI location = restTemplate.postForLocation("http://localhost:8085/rest/stocks", stock);
		Stock stock2 = restTemplate.getForObject(location, Stock.class);

		MatcherAssert.assertThat(stock2.getEmail(), Matchers.equalTo(stock.getEmail()));
		MatcherAssert.assertThat(stock2.getBrand(), Matchers.equalTo(stock.getBrand()));

	}

	@Test
	public void testUpdateStock() {

		RestTemplate restTemplate = new RestTemplate();
		Stock stock = restTemplate.getForObject("http://localhost:8085/rest/stocks/4", Stock.class);

		MatcherAssert.assertThat(stock.getName(), Matchers.equalTo("XXX"));
		stock.setName("XXXWW");
		restTemplate.put("http://localhost:8085/rest/stocks/4", stock);
		stock = restTemplate.getForObject("http://localhost:8085/rest/stocks/4", Stock.class);
		MatcherAssert.assertThat(stock.getName(), Matchers.equalTo("XXXWW"));

	}

	@Test
	public void testDeleteStock() {
		restTemplate.delete("http://localhost:8085/rest/stocks/15");

		try {
			restTemplate.getForEntity("http://localhost:8085/rest/stocks/15", Stock.class);
			Assert.fail("Should have not returned stock");
		} catch (HttpClientErrorException ex) {
			MatcherAssert.assertThat(ex.getStatusCode().value(), Matchers.equalTo(404));
		}

	}

}
