package com.example.Blogify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.payloads.CommentDTO;
import com.example.Blogify.service.impl.CommentImplemention;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	CommentImplemention service;
	
	@PostMapping("/create")
	public ResponseEntity<CommentDTO> insetComment(@RequestBody CommentDTO dto,@RequestParam Integer postid,@RequestParam Integer userid) {
		CommentDTO Commentdto=  service.insetComment(dto, postid, userid);
		return new ResponseEntity<CommentDTO>(Commentdto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/all-comments/post/")
	public ResponseEntity<List<CommentDTO>> viewAllComments(@RequestParam Integer postid){
		List<CommentDTO> comentdtos=service.viewAllComments(postid);
		return new ResponseEntity<List<CommentDTO>>(comentdtos,HttpStatus.ACCEPTED);
	}
	@GetMapping("/all-comments/user/")
	public ResponseEntity<List<CommentDTO>>  getCommentsByUser(@RequestParam Integer userid){
		List<CommentDTO> comentdtos=service.getCommentsByUser(userid);
		return new ResponseEntity<List<CommentDTO>>(comentdtos,HttpStatus.ACCEPTED);
	}

}
