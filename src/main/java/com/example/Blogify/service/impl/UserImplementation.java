package com.example.Blogify.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Blogify.config.AppConstants;
import com.example.Blogify.entities.Role;
import com.example.Blogify.entities.UserClass;
import com.example.Blogify.payloads.UserDTO;
import com.example.Blogify.repositories.RoleRepository;
import com.example.Blogify.repositories.UserRepository;
import com.example.Blogify.service.UserServices;
import com.example.Blogify.exception.*;
@Service
public class UserImplementation implements UserServices {
	@Autowired
	UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Override
	public UserDTO createUser(UserDTO userdto) {
		UserClass user = this.getUser(userdto);
		UserClass updateUser=userRepo.save(user);
		return this.getUserDTO(updateUser);

		
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserClass> users=userRepo.findAll();
		List<UserDTO> userdto=users.stream().map(user->this.getUserDTO(user)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer id) {
	    UserClass user = userRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

	    user.setAbout(userdto.getAbout());
	    user.setEmail(userdto.getEmail());
	    user.setId(userdto.getId());
	    user.setName(userdto.getName());
	    user.setPassword(encoder.encode(userdto.getPassword()));

	    UserClass updatedUser = userRepo.save(user);
	    return getUserDTO(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer id) {
	    UserClass user = userRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
	    return getUserDTO(user);
	}

	@Override
	public Boolean deleteUser(Integer id) {
	    UserClass user = userRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
	    userRepo.delete(user);
	    return true;
	}

	public UserDTO getUserDTO(UserClass user) {
	    return mapper.map(user, UserDTO.class);
	}

	public UserClass getUser(UserDTO userdto) {
	    return mapper.map(userdto, UserClass.class);
	}

	@Override
	public UserDTO registerUser(UserDTO dto) {
	    UserClass user = mapper.map(dto, UserClass.class);
	    user.setPassword(encoder.encode(user.getPassword()));
	    Role role = roleRepo.findById(AppConstants.NORMAL_USER).orElseThrow();
	    user.getRoles().add(role);
	    UserClass newUser = userRepo.save(user);
	    return mapper.map(newUser, UserDTO.class);
	}


}
