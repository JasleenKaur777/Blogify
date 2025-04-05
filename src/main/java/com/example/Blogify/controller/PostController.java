package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.service.impl.PostImplemention;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	PostImplemention service;
	
	@PostMapping("/category/{category_id}/user/{user_id}/create-post")
   public ResponseEntity<PostDTO> insertPost(@RequestBody PostDTO postdto,@PathVariable Integer category_id,@PathVariable Integer user_id) {
	  PostDTO dto= service.insertPost(postdto, category_id, user_id);
	  return new ResponseEntity<PostDTO>(dto,HttpStatus.ACCEPTED);
   }
	

}
