package com.izibiz.springboot.stockrestapi.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.izibiz.springboot.stockrestapi.model.Users;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    
    private RestTemplate restTemplate;
	private Users users;


    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager, final CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }
    
    
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			try {

				//String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				
				getRestTemplate().postForLocation("http://localhost:8085/login", users);
				
				/*
				 * username = json.get("email").asText(); password =
				 * json.get("password").asText();
				 */
				
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword());

				return authenticationManager.authenticate(token);
			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
				response.setStatus(400);
			}
		}
		try {
			response.sendError(400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	public RestTemplate getRestTemplate() {
		return restTemplate;
	}



	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}



	public Users getUsers() {
		return users;
	}



	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	 
}

