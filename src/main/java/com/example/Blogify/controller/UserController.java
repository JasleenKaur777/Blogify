package com.example.Blogify.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.payloads.ResponseMsg;
import com.example.Blogify.payloads.UserDTO;
import com.example.Blogify.service.impl.UserImplementation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserImplementation service;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto) {
	    UserDTO savedUser = service.createUser(userdto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	@GetMapping("/get-all-user")
	public ResponseEntity<List<UserDTO>>  getAllUser(){
		List<UserDTO> users=service.getAllUser();
		return  ResponseEntity.ok(users);
	}
	@PutMapping("/update-user/{userId}")
	public  ResponseEntity<ResponseMsg>  updateUser(@Valid @RequestBody UserDTO userdto,@PathVariable Integer userId) {
		UserDTO user=service.updateUser(userdto, userId);
		if(user!=null) {
			return ResponseEntity.ok(new ResponseMsg("message", "Updation successfully",true));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("message", "User id is not found",false));
		}
	}
	@GetMapping("/get-user-by-id/{id}")
	public ResponseEntity<UserDTO>  getUserById(@PathVariable Integer id) {
		UserDTO user=service.getUserById(id);
		if(user!=null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.ok(null);
		}
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<ResponseMsg> deleteUser(@PathVariable Integer id) {
		Boolean b=service.deleteUser(id);
		if(b==true) {
			return ResponseEntity.ok(new ResponseMsg("message", "Deletion successfully",true));
		}
		else {
			return ResponseEntity.ok(new ResponseMsg("message", "User id is not found",false));
		}
	}

}
