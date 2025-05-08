package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.exception.ApiException;
import com.example.Blogify.payloads.JWTAuthRequest;
import com.example.Blogify.payloads.JWTAuthResponse;
import com.example.Blogify.payloads.UserDTO;
import com.example.Blogify.security.CustomUserDetailService;
import com.example.Blogify.security.JWTTokenHelper;
import com.example.Blogify.service.impl.UserImplementation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailService;
    
    @Autowired
    private UserImplementation service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTAuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );

            UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
            String token = jwtTokenHelper.generateToken(userDetails.getUsername());

            JWTAuthResponse response = new JWTAuthResponse();
            response.setToken(token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
        	throw new ApiException("Invalid username or password!");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userdto){
    UserDTO dto=	service.registerUser(userdto);
    return new ResponseEntity<UserDTO>(dto,HttpStatus.ACCEPTED);
    
    }

}
