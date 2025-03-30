package com.example.Blogify.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.User;
import com.example.Blogify.payloads.UserDTO;
import com.example.Blogify.repositories.UserRepository;
import com.example.Blogify.service.UserServices;
import com.example.Blogify.exception.*;
@Service
public class UserImplementation implements UserServices {
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDTO createUser(UserDTO userdto) {
		User user = this.getUser(userdto);
		User updateUser=userRepo.save(user);
		return this.getUserDTO(updateUser);

		
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users=userRepo.findAll();
		List<UserDTO> userdto=users.stream().map(user->this.getUserDTO(user)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		user.setAbout(userdto.getAbout());
		user.setEmail(user.getEmail());
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		User updateUser=userRepo.save(user);
		UserDTO userdto1=this.getUserDTO(updateUser);
		return userdto1;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		UserDTO userdto1=this.getUserDTO(user);
		return userdto1;
	}

	@Override
	public Boolean deleteUser(Integer id) {
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		userRepo.delete(user);
		if(user==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	public UserDTO getUserDTO(User user) {
		UserDTO userdto = new UserDTO();
		userdto.setId(user.getId());
		userdto.setAbout(user.getAbout());
		userdto.setEmail(user.getEmail());
		userdto.setName(user.getName());
		userdto.setPassword(user.getPassword());
		return userdto;

	}

	public User getUser(UserDTO userdto) {
		User user = new User();
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		return user;
	}

}
