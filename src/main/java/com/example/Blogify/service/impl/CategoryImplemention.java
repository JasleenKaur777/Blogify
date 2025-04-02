package com.example.Blogify.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blogify.entities.Category;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.payloads.CategoryDTO;
import com.example.Blogify.repositories.CategoryRepository;
import com.example.Blogify.service.CategoryService;

@Service
public class CategoryImplemention implements CategoryService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepository repo;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categorydto) {
		Category category= mapper.map(categorydto,Category.class);
		repo.save(category);
		CategoryDTO dto=mapper.map(category, CategoryDTO.class);
		return dto;
	}

	@Override
	public List<CategoryDTO> viewAllCategory() {
		List<Category> category=repo.findAll();
		List<CategoryDTO> categorydto=category.stream().map(c->mapper.map(c, CategoryDTO.class)).collect(Collectors.toList());
		return categorydto;
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categorydto, Integer id) {
	Category category=	repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Category id",id));
	CategoryDTO dto =  mapper.map(category, CategoryDTO.class);
	dto.setCategory_id(category.getCategory_id());
	dto.setTitle(category.getTitle());
	dto.setDescription(category.getDescription());
	repo.save(category);
		return dto;
	}

	@Override
	public Boolean deleteCategory(Integer id) {
	Category category=	repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", id));
	if(category!=null) {
		repo.delete(category);
		return true;
	}
	else {
		return false;
	}
		
	}

	@Override
	public CategoryDTO getCategory(Integer id) {
		Category category=	repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", id));
		CategoryDTO dto= mapper.map(category, CategoryDTO.class);
		return dto;
	}

}
