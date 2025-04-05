package com.example.Blogify.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.Category;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.User;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.repositories.CategoryRepository;
import com.example.Blogify.repositories.PostRepository;
import com.example.Blogify.repositories.UserRepository;
import com.example.Blogify.service.PostService;

@Service
public class PostImplemention implements PostService {
	@Autowired
	PostRepository post_repo;

	@Autowired
	ModelMapper mapper;
	@Autowired
	CategoryRepository category_repo;
	@Autowired
	UserRepository user_repo;

	@Override
	public PostDTO insertPost(PostDTO postdto, Integer category_id, Integer user_id) {
		Category category=  category_repo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", category_id));
		User user=user_repo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User", "user_id", user_id));
		Post post = mapper.map(postdto, Post.class);
		post.setAdded_Date(new Date());
		post.setCategory(category);
		post.setContent(postdto.getContent());
		post.setImageName("default.png");
		post.setPost_Title(postdto.getPost_title());
		post.setUser(user);
        post_repo.save(post);
		return mapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> viewPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO updatePost(Integer id, PostDTO postdto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletePost(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO getPost(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
