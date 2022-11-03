package com.izibiz.springboot.stockrestapi.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.izibiz.springboot.stockrestapi.model.Users;


public class CustomUserDetails implements UserDetails {

	 private static final long serialVersionUID = 1L;
	private Users users;

	    public CustomUserDetails(Users users) {
	        super();
	        this.users = users;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return Collections.singleton(new SimpleGrantedAuthority(users.getRole()));
	    }

	    @Override
	    public String getPassword() {
	        return users.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return users.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	    
	    
}
