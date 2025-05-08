package com.example.Blogify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blogify.payloads.CategoryDTO;
import com.example.Blogify.payloads.ResponseMsg;
import com.example.Blogify.service.impl.CategoryImplemention;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "bearerAuth") // Apply to this controller
public class CategoryController {
	@Autowired
private CategoryImplemention service;
	@PostMapping("/create-category")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categorydto) {
		CategoryDTO dto= service.createCategory(categorydto);
		return new ResponseEntity<CategoryDTO>(dto,HttpStatus.ACCEPTED);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> viewAllCategory(){
		List<CategoryDTO> dtos=  service.viewAllCategory();
		return ResponseEntity.ok(dtos);
	}
	@PutMapping("/update-category/{id}")
	public ResponseEntity<ResponseMsg> updateCategory(@Valid @RequestBody CategoryDTO categorydto,@PathVariable Integer id) {
		CategoryDTO dto= service.updateCategory(categorydto, id);
		if(dto!=null) {
			return ResponseEntity.ok( new ResponseMsg("message", "updation successfull", true));
		}
		else {
			return ResponseEntity.ok( new ResponseMsg("message", "updation is not successfull", false));
		}
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseMsg> deleteCategory(@PathVariable Integer id) {
		Boolean b=service.deleteCategory(id);
		if(b==true) {
			return ResponseEntity.ok( new ResponseMsg("message", "deletion successfull", true));
		}
		else {
			return ResponseEntity.ok( new ResponseMsg("message", "deletion is not successfull", false));
		}
	}
	@GetMapping("/get-category/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) {
		CategoryDTO dto= service.getCategory(id);
		return new ResponseEntity<CategoryDTO>(dto,HttpStatus.ACCEPTED);
	}
}
