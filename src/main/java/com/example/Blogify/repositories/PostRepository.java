package com.example.Blogify.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Blogify.entities.Category;
import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.UserClass;



public interface PostRepository extends JpaRepository<Post, Integer> {
     Page<Post> findByUser(UserClass user,Pageable p);
     Page<Post> findByCategory(Category category,Pageable p);
     @Query("select p from Post p where p.postTitle like:key")
     Page<Post> findByPostTitleContaining(@Param("key") String keyword,Pageable p);

}
