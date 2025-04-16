package com.example.Blogify.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.Category;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.User;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.payloads.PostResponse;
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
	
	@Autowired
	PostResponse response;

	@Override
	public PostDTO insertPost(PostDTO postdto, Integer category_id, Integer user_id) {
		Category category = category_repo.findById(category_id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", category_id));
		User user = user_repo.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", user_id));
		Post post = mapper.map(postdto, Post.class);
		post.setAdded_Date(new Date());
		post.setCategory(category);
		post.setContent(postdto.getContent());
		post.setImageName("default.png");
		post.setPostTitle(postdto.getpostTitle());
		post.setUser(user);
		post_repo.save(post);
		return mapper.map(post, PostDTO.class);
	}

	public PostResponse viewPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort order=null;
		if(sortDir.equalsIgnoreCase("asc")) {
		order=Sort.by(sortBy).ascending();
		}
		else {
			order=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize, order);
		Page<Post> pageContent=post_repo.findAll(p);
		List<Post> posts=pageContent.getContent();
		List<PostDTO> postdto = posts.stream().map(post -> mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		response.setContent(postdto);
		response.setLastPage(pageContent.isLast());
		response.setPageNumber(pageNumber);
		response.setPageSize(pageSize);
		response.setTotalElements(pageContent.getNumberOfElements());
		response.setTotalPages(pageContent.getTotalPages());
		return response;
	}

	@Override
	public PostDTO updatePost(Integer id, PostDTO postdto,Integer category_id) {
		Category category = category_repo.findById(category_id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", category_id));
		Post post = post_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", id));
		post.setPostTitle(postdto.getpostTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		post.setCategory(category);
		return mapper.map(post, PostDTO.class);
	}

	@Override
	public Boolean deletePost(Integer id) {
		Post post = post_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", id));
		if(post!=null) {
			post_repo.delete(post);
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public PostDTO getPost(Integer id) {
		Post post = post_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", id));
		return mapper.map(post, PostDTO.class);
	}

	public PostResponse getPostByCategory(int pageNumber, int pageSize, Integer category_id,String sortBy,String sortDir) {
		Sort order=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			order=Sort.by(sortBy).ascending();
		}
		else {
			order=Sort.by(sortBy).descending();
		}
	    Pageable p = PageRequest.of(pageNumber, pageSize,order);
	    
	    Category category = category_repo.findById(category_id)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", category_id));

	    Page<Post> pageContent = post_repo.findByCategory(category, p); 
	    List<Post> posts = pageContent.getContent();

	    List<PostDTO> dto = posts.stream()
	                             .map(post -> mapper.map(post, PostDTO.class))
	                             .collect(Collectors.toList());

	    response.setContent(dto);
	    response.setLastPage(pageContent.isLast());
	    response.setPageNumber(pageNumber);
	    response.setPageSize(pageSize);
	    response.setTotalElements((int) pageContent.getTotalElements());
	    response.setTotalPages(pageContent.getTotalPages());

	    return response;
	}


	public PostResponse getPostByUser(Integer user_id, int pageNumber, int pageSize) {
	    User user = user_repo.findById(user_id)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "user_id", user_id));

	    Pageable p = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pageContent = post_repo.findByUser(user, p);

	    List<PostDTO> dto = pageContent.getContent().stream()
	            .map(post -> mapper.map(post, PostDTO.class))
	            .collect(Collectors.toList());

	    response.setContent(dto);
	    response.setLastPage(pageContent.isLast());
	    response.setPageNumber(pageNumber);
	    response.setPageSize(pageSize);
	    response.setTotalElements((int) pageContent.getTotalElements());
	    response.setTotalPages(pageContent.getTotalPages());

	    return response;
	}


	public PostResponse searchPost(String keyword, int pageNumber, int pageSize) {
	    Pageable p = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pageContent = post_repo.findByPostTitleContaining("%"+keyword+"%", p);

	    if (pageContent.isEmpty()) {
	        throw new ResourceNotFoundException("Post title", "keyword", 0);
	    }

	    else {
	    	 List<PostDTO> dto = pageContent.getContent().stream()
	 	            .map(post -> mapper.map(post, PostDTO.class))
	 	            .collect(Collectors.toList());

	 	    response.setContent(dto);
	 	    response.setLastPage(pageContent.isLast());
	 	    response.setPageNumber(pageNumber);
	 	    response.setPageSize(pageSize);
	 	    response.setTotalElements((int) pageContent.getTotalElements());
	 	    response.setTotalPages(pageContent.getTotalPages());
	 	   return response;
	    }
	   

	    
	}

	


	


}
