package com.example.Blogify.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.Category;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.User;



public interface PostRepository extends JpaRepository<Post, Integer> {
     Page<Post> findByUser(User user,Pageable p);
     Page<Post> findByCategory(Category category,Pageable p);
     Page<Post> findByPostTitleContaining(String keyword,Pageable p);

}
