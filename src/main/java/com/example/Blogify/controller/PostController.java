package com.example.Blogify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.payloads.PostResponse;
import com.example.Blogify.payloads.ResponseMsg;
import com.example.Blogify.service.impl.PostImplemention;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostImplemention service;

	@PostMapping("/category/{category_id}/user/{user_id}/create-post")
	public ResponseEntity<PostDTO> insertPost(@RequestBody PostDTO postdto, @PathVariable Integer category_id,
			@PathVariable Integer user_id) {
		PostDTO dto = service.insertPost(postdto, category_id, user_id);
		return new ResponseEntity<PostDTO>(dto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> viewPosts(@RequestParam(defaultValue = "0",required = false)Integer pageNumber,@RequestParam(defaultValue = "5",required = false)Integer pageSize) {
	PostResponse dto=	service.viewPosts(pageNumber,pageSize);
	return new ResponseEntity<PostResponse>(dto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Integer id){
		PostDTO dto= service.getPost(id);
		return new ResponseEntity<PostDTO>(dto,HttpStatus.ACCEPTED);
	}
    
	@GetMapping("/category/{category_id}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(
	        @PathVariable Integer category_id,
	        @RequestParam(defaultValue = "0") Integer pageNumber,
	        @RequestParam(defaultValue = "5") Integer pageSize) {

	    if (pageSize < 1) pageSize = 5;  

	    PostResponse dto = service.getPostByCategory(pageNumber, pageSize, category_id);
	    return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/user/{user_id}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer user_id,@RequestParam(defaultValue = "0")Integer pageNumber,@RequestParam(defaultValue = "5")Integer pageSize){
		PostResponse dto=service.getPostByUser(user_id,pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/{id}/category/{category_id}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable Integer id,@RequestBody PostDTO postdto,@PathVariable Integer category_id){
		PostDTO dto= service.updatePost(id, postdto,category_id);
		return new ResponseEntity<PostDTO>(dto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseMsg deletePost(@PathVariable Integer id) {
		Boolean isDeleted= service.deletePost(id);
		if(isDeleted) {
			return new ResponseMsg("message","Post is deleted", true);
		}
		else {
			return new ResponseMsg("message", "Post is not deleted", false);
		}
	}
	@GetMapping("/posts/search")
	public ResponseEntity<PostResponse> searchPost(@RequestParam String keyword,@RequestParam(defaultValue = "0")Integer pageNumber,@RequestParam(defaultValue = "5")Integer pageSize) {
		PostResponse dto = service.searchPost(keyword, pageNumber, pageSize);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
