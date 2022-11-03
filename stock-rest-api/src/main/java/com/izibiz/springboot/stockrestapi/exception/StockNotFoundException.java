package com.izibiz.springboot.stockrestapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {

	public StockNotFoundException(String args0) {

	}
}
