package com.izibiz.springboot.stockrestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.izibiz.springboot.stockrestapi.dao.UsersDao;
import com.izibiz.springboot.stockrestapi.model.Users;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UsersDao usersDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersDao.findByUsername(username);
        if(users ==null) {
            throw new UsernameNotFoundException("Users Not Found");
        }
        return new CustomUserDetails(users);
    }
}
