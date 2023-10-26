package com.techinfoguider.post.controller;

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

import com.techinfoguider.post.payload.CategoryDto;
import com.techinfoguider.post.payload.ResponceApi;
import com.techinfoguider.post.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category/api")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	// CREATE CATEGORY

	@PostMapping("/create-category")
	private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createdCategory = this.categoryServices.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}

	// GET CATEGORY
	@GetMapping("/")
	private ResponseEntity<List<CategoryDto>> getCategoryList(){
		List<CategoryDto> allCategory = this.categoryServices.getAllCategory();
//		allCategory.stream().map(null)
		return ResponseEntity.ok(allCategory);
	}

	// UPDATE CATEGORY
	@PutMapping("/{categoryid}")
	private ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable(name = "categoryid") Integer categoryId){
		CategoryDto updatedCategory = this.categoryServices.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
		
		
	}
	

	// DELETE CATEGORY
	@DeleteMapping("/{categoryid}")
	private ResponseEntity<ResponceApi> deleteCategory(@PathVariable(name = "categoryid") Integer categoryId){
		this.categoryServices.deleteCategory(categoryId);
		return new ResponseEntity<ResponceApi>(new ResponceApi("Category Deleted Successfully", true), HttpStatus.OK);
	}
	

	// GETCATEGORY BY ID
	@GetMapping("/{categoryid}")
	private ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "categoryid") Integer categoryId){
//	 CategoryDto categoryById = this.categoryServices.getCategoryById(categoryId);
//	 return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);
		return ResponseEntity.ok(this.categoryServices.getCategoryById(categoryId));
	}
	
	
	
	
}
