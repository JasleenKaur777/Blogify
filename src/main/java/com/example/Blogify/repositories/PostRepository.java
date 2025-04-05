package com.example.Blogify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.Category;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.User;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {
     List<Post> findByUser(User user);
     List<Post> findByCategory(Category category);
}
