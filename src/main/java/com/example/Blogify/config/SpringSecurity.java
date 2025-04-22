package com.example.Blogify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Blogify.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurity  {
	
	@Autowired
	private CustomUserDetailService customUserDetail;
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for testing/API usage
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/public/**").permitAll() // public APIs
	                .anyRequest().authenticated() // all other requests need authentication
	            )
	            .httpBasic(); // Enable HTTP Basic Authentication

	        return http.build();
	    }

	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		 return config.getAuthenticationManager();
	 }
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(customUserDetail);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	 
	     //Password encoder bean
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
