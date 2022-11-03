package com.izibiz.springboot.stockrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = StockProperties.class)
public class StockRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockRestApiApplication.class, args);
	}

}
