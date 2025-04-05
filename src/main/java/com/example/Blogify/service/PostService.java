package com.example.Blogify.service;

import java.util.List;

import com.example.Blogify.payloads.PostDTO;

public interface PostService {

	PostDTO insertPost(PostDTO postdto,Integer category_id,Integer user_id);
	List<PostDTO> viewPosts();
	PostDTO updatePost(Integer id,PostDTO postdto);
	Boolean deletePost(Integer id);
	PostDTO getPost(Integer id);
	List<PostDTO> getPostByCategory(Integer category_id);
	List<PostDTO> getPostByUser(Integer user_id);
	List<PostDTO> searchPost(String keyword);
}
