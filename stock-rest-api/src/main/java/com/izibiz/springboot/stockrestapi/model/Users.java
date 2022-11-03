package com.izibiz.springboot.stockrestapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String role;
	private String token;

	
	public Users(String username, String password,Long id,String role,String token) {
		this.username = username;
		this.password = password;
		this.id=id;
		this.role=role;
		this.token=token;
		
	}
	
	public Users() {
		
	}

	@Id
	@GeneratedValue(generator = "stock_gen")
	@SequenceGenerator(name = "stock_gen", sequenceName = "SEQ_USERS", allocationSize = 1, initialValue = 1)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
