package com.techinfoguider.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techinfoguider.post.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
