package com.example.Blogify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
