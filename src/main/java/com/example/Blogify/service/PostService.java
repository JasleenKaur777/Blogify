package com.example.Blogify.service;



import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.payloads.PostResponse;

public interface PostService {

	PostDTO insertPost(PostDTO postdto,Integer category_id,Integer user_id);
	public PostResponse viewPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDTO updatePost(Integer id,PostDTO postdto,Integer category_id);
	Boolean deletePost(Integer id);
	PostDTO getPost(Integer id);
	PostResponse getPostByCategory(int pageNumber, int pageSize, Integer category_id,String sortBy,String sortDir);
	PostResponse getPostByUser(Integer user_id, int pageNumber, int pageSize);
	PostResponse searchPost(String keyword,int pageNumber, int pageSize);
}
