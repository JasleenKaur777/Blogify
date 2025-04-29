package com.example.Blogify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.UserClass;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by email (or username, depending on how you store it)
        UserClass user = repo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

        // Return the user as UserDetails
        return user;
    }
}
