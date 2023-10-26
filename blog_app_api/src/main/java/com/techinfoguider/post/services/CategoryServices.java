package com.techinfoguider.post.services;

import java.util.List;

import com.techinfoguider.post.payload.CategoryDto;

public interface CategoryServices {
	
	//CREATE CATEGORY
	
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	//GET CATEGORY
	 
	 List<CategoryDto> getAllCategory();
	
	//UPDATE CATEGORY
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//DELETE CATEGORY
	 void deleteCategory(Integer categoryId);
	
	//GET CATEGORY BY ID
	 CategoryDto getCategoryById(Integer categoryId);
	
	

}
