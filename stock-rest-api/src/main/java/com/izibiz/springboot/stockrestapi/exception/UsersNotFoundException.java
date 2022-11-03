package com.izibiz.springboot.stockrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UsersNotFoundException extends RuntimeException {

	public UsersNotFoundException(String args0) {

	}
}
