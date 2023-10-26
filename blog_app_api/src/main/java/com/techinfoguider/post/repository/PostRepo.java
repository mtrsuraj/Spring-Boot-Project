package com.techinfoguider.post.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techinfoguider.post.Entity.Category;
import com.techinfoguider.post.Entity.Post;
import com.techinfoguider.post.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
//	@Query("select post_title from Post  where post_title like :keys")
	List<Post> findByPostTitle(String keyword);

}
