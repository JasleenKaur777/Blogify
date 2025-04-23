package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.Blogify.payloads.JWTAuthRequest;
import com.example.Blogify.payloads.JWTAuthResponse;
import com.example.Blogify.security.CustomUserDetailService;
import com.example.Blogify.security.JWTTokenHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTAuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
            String token = jwtTokenHelper.generateToken(userDetails.getUsername());

            JWTAuthResponse response = new JWTAuthResponse();
            response.setToken(token);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Invalid username or password!");
        }
    }
}
