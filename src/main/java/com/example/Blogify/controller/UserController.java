package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.payloads.UserDTO;
import com.example.Blogify.service.impl.UserImplementation;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserImplementation service;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto) {
		UserDTO userdto1 = service.createUser(userdto);

		return new ResponseEntity<>(userdto1, HttpStatus.CREATED);

	}

}
