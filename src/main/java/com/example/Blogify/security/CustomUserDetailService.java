package com.example.Blogify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Blogify.entities.User;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.repositories.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
 
	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email", username));
		return user;
	}

}
