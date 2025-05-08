package com.example.Blogify.controller;


import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Blogify.config.AppConstants;
import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.payloads.PostResponse;
import com.example.Blogify.payloads.ResponseMsg;
import com.example.Blogify.repositories.PostRepository;
import com.example.Blogify.service.impl.FileServiceImplemention;
import com.example.Blogify.service.impl.PostImplemention;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostImplemention service;
	
	@Autowired
	private FileServiceImplemention fileService;
	
	
	@Value("${project.image}")
	String path;

	@PostMapping("/category/{category_id}/user/{user_id}/create-post")
	public ResponseEntity<PostDTO> insertPost(@RequestBody PostDTO postdto, @PathVariable Integer category_id,
			@PathVariable Integer user_id) {
		PostDTO dto = service.insertPost(postdto, category_id, user_id);
		return new ResponseEntity<PostDTO>(dto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> viewPosts
	(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
	 @RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
	 @RequestParam(required=false)String sortBy,
	@RequestParam(required=false)String sortDir){
	PostResponse dto=	service.viewPosts(pageNumber, pageSize, sortBy,sortDir);
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
	        @RequestParam(defaultValue = "5") Integer pageSize,
	        @RequestParam(required=false)String sortBy,
	        @RequestParam(required=false)String sortDir) {

	    if (pageSize < 1) pageSize = 5;  

	    PostResponse dto = service.getPostByCategory(pageNumber, pageSize, category_id,sortBy,sortDir);
	    return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/user/{user_id}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer user_id,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER)Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE)Integer pageSize){
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
	public ResponseEntity<PostResponse> searchPost(@RequestParam String keyword,
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER)Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE)Integer pageSize) {
		PostResponse dto = service.searchPost(keyword, pageNumber, pageSize);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@PostMapping(value = "/posts/{postId}/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Upload post image", description = "Uploads an image for the given post")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
	    @ApiResponse(responseCode = "400", description = "Invalid image or not a multipart request"),
	    @ApiResponse(responseCode = "404", description = "Post not found")
	})
	public ResponseEntity<PostDTO> uploadPostImage(
	        @PathVariable Integer postId,
	        @RequestParam("image") MultipartFile imageFile) throws IOException {

	    // Step 1: Upload the image and get the stored filename
	    String fileName = fileService.uploadsImage(postId, imageFile);

	    // Step 2: Get the post and update its image name
	    PostDTO post = service.getPost(postId);
	    post.setImageName(fileName);

	    // Step 3: Save the updated post
	    PostDTO updatedPost = service.updatePost(postId, post, post.getCategory().getCategory_id());

	    return ResponseEntity.ok(updatedPost);
	}
	@GetMapping(value = "/images/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
	    InputStream in = fileService.getResponse(path, filename);
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(in, response.getOutputStream());
	}



}
