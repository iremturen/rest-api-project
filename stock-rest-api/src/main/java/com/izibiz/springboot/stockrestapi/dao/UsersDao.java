package com.izibiz.springboot.stockrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.izibiz.springboot.stockrestapi.model.Stock;
import com.izibiz.springboot.stockrestapi.model.Users;

@Repository
public interface UsersDao extends JpaRepository<Users, Long>, CrudRepository<Users, Long> {

	Users findByUsername(String username);

	Users save(Users token);

}
