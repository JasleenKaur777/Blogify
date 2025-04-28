package com.example.Blogify.service;

import java.util.List;

import com.example.Blogify.payloads.UserDTO;

public interface UserServices {
UserDTO createUser(UserDTO user);
List<UserDTO> getAllUser();
UserDTO updateUser(UserDTO user,Integer id);
UserDTO getUserById(Integer id);
Boolean deleteUser(Integer id);
UserDTO registerUser(UserDTO dto);
}
