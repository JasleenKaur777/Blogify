package com.example.Blogify.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.Comment;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.User;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.payloads.CommentDTO;
import com.example.Blogify.repositories.CommentRepository;
import com.example.Blogify.repositories.PostRepository;
import com.example.Blogify.repositories.UserRepository;
import com.example.Blogify.service.CommentService;

@Service
public class CommentImplemention implements CommentService {
  
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	CommentRepository repo;
	
	@Autowired
	PostImplemention post_service;
	
	@Override
	public CommentDTO insetComment(CommentDTO dto,Integer postid,Integer userid) {
		Post post=postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postid));
		User user=userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "User id", userid));
		Comment comment=new Comment();
		comment.setContent(dto.getContent());
		comment.setPosts(post);
		comment.setUser(user);
		repo.save(comment);
		return mapper.map(comment, CommentDTO.class);
	}

	@Override
	public List<CommentDTO> viewAllComments(Integer postid) {
		Post post=postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postid));
		List<Comment> comments=repo.findByPosts(post);
		List<CommentDTO> comentdtos=comments.stream().map(comment->mapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return comentdtos;
	}

	@Override
	public List<CommentDTO> getCommentsByUser(Integer userid) {
		User user=userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "User id", userid));
		List<Comment> comments=repo.findByUser(user);
		List<CommentDTO> comentdtos=comments.stream().map(comment->mapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return comentdtos;
	}

	@Override
	public CommentDTO updateComment(Integer commentid,CommentDTO commentdto) {
		Comment comment=repo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentid));
		comment.setContent(commentdto.getContent());
		repo.save(comment);
		return mapper.map(comment, CommentDTO.class);
	}

	@Override
	public Boolean deleteComment(Integer commentid) {
		Comment comment=repo.findById(commentid).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentid));
		repo.delete(comment);
		return true;
	}

	

}
