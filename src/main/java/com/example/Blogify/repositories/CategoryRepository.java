package com.example.Blogify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
