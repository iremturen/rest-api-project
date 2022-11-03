package com.izibiz.springboot.stockrestapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.izibiz.springboot.stockrestapi.model.Users;
import com.izibiz.springboot.stockrestapi.security.CustomUserDetailsService;
import com.izibiz.springboot.stockrestapi.service.UsersService;
import com.izibiz.springboot.stockrestapi.util.AuthenticationRequest;
import com.izibiz.springboot.stockrestapi.util.AuthenticationResponse;
import com.izibiz.springboot.stockrestapi.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class UsersController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	/*
	 * @GetMapping("/login") public ResponseEntity<Object> login(@RequestBody String
	 * username) { return ResponseEntity.ok(usersService.findByUsername(username));
	 * }
	 */
	
	@PostMapping("/login")
	public AuthenticationResponse authenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (authenticationRequest.getUsername(),authenticationRequest.getPassword()));		
		}catch(BadCredentialsException e){		
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt =jwtUtil.generateToken(userDetails);
		
		Users usersToken =usersService.findByUsername(authenticationRequest.getUsername());
		usersToken.setToken(jwt);
		usersService.updateToken(usersToken);
		
        return new AuthenticationResponse(jwt);
	}

	
	
}
