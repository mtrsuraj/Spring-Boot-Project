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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techinfoguider.post.config.AppConstant;
import com.techinfoguider.post.payload.PostDto;
import com.techinfoguider.post.payload.PostResponce;
import com.techinfoguider.post.payload.ResponceApi;
import com.techinfoguider.post.services.PostServices;

@RestController
@RequestMapping("post/api")
public class PostController {

	@Autowired
	private PostServices postServices;

	//CREATE POST
	@PostMapping("/user/{userid}/category/{categoryid}/create-post")
	private ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable(name = "userid") Integer userId, 
			@PathVariable(name = "categoryid") Integer categoryId) {
		PostDto createdPoast = this.postServices.createPoast(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPoast, HttpStatus.CREATED);
	}
	
	//UPDATE POST
	@PutMapping("/update-post/{postId}")
	private ResponseEntity<ResponceApi> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "postId") Integer postId){
		PostDto updatedPost = this.postServices.updatePost(postDto, postId);
		return new ResponseEntity<ResponceApi>(new ResponceApi("Post Updated Successfully", true), HttpStatus.OK);
		
	}
	
	//FIND SINGLE POST
	@GetMapping("post/{postId}")
	private ResponseEntity<PostDto> findSinglePost(@PathVariable(name = "postId") Integer postId){
		PostDto postById = this.postServices.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}
	
	//GET ALL POST
	@GetMapping("/")
	private ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber, 
			 @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			 @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false)String sortBy,
			 @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir
			 ){
		 PostResponce allPost = this.postServices.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponce>(allPost, HttpStatus.OK);
	}
	
	//POST FIND BY CATEGORY
	@GetMapping("category/{categoryId}/posts")
	private ResponseEntity<List<PostDto>>  getPostByCategory(@PathVariable(name = "categoryId") Integer categoryId){
		List<PostDto> allPostByCategory = this.postServices.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);
	}

	//POST FIND BY USER
	@GetMapping("user/{userId}/posts")
	private ResponseEntity<List<PostDto>> getPostByUser(@PathVariable(name = "userId") Integer userId){
		List<PostDto> allPostByUser = this.postServices.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(allPostByUser, HttpStatus.OK);
	}
	
	//SEARCH POST
	@GetMapping("search-post/{key}")
	private List<PostDto> searchPost(@PathVariable(name = "key") String key){
		List<PostDto> searchPost = this.postServices.searchPost(key);
		return searchPost;
				
	}
	
	//DELETE POST
	@DeleteMapping("/{postId}")
	private ResponseEntity<ResponceApi> deletePost(@PathVariable(name = "postId") Integer postId){
		this.postServices.deletePost(postId);
		return new ResponseEntity<ResponceApi>(new ResponceApi("Post Deleted successfully", true), HttpStatus.OK);
		
	}
	
}
