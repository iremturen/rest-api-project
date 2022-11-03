package com.izibiz.springboot.stockrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.springboot.stockrestapi.dao.UsersDao;
import com.izibiz.springboot.stockrestapi.model.Stock;
import com.izibiz.springboot.stockrestapi.model.Users;

@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService{

	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public Users findByUsername(String username) {
		return usersDao.findByUsername(username);
	}

	@Override
	public Users updateToken(Users token) {
		Users usersToken =usersDao.findById(token.getId()).get();
		usersToken.setPassword(token.getPassword());
		usersToken.setRole(token.getRole());
		usersToken.setUsername(token.getUsername());
		usersToken.setToken(token.getToken());
		return usersDao.save(token);
	}

}
