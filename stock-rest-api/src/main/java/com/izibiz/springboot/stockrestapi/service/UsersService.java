package com.izibiz.springboot.stockrestapi.service;

import com.izibiz.springboot.stockrestapi.model.Users;

public interface UsersService {

	Users findByUsername(String username);
	
	Users updateToken(Users token) ;
}
