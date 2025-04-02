package com.example.Blogify.service;

import java.util.List;

import com.example.Blogify.payloads.CategoryDTO;

public interface CategoryService {
CategoryDTO createCategory(CategoryDTO categorydto);
List<CategoryDTO> viewAllCategory();
CategoryDTO updateCategory(CategoryDTO categorydto,Integer id);
Boolean deleteCategory(Integer id);
CategoryDTO getCategory(Integer id);
}
