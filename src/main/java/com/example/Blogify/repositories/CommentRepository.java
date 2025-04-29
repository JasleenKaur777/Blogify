package com.example.Blogify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.Comment;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.UserClass;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
List<Comment> findByPosts(Post posts);
List<Comment> findByUser(UserClass user);
}
