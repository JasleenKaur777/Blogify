package com.example.Blogify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Blogify.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
