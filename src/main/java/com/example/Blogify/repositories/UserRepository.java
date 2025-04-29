package com.example.Blogify.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.UserClass;

public interface UserRepository extends JpaRepository<UserClass, Integer> {
Optional<UserClass> findByEmail(String email);
}
