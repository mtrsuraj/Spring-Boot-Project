package com.techinfoguider.post.services;

import java.util.List;



import com.techinfoguider.post.payload.PostDto;
import com.techinfoguider.post.payload.PostResponce;

public interface PostServices {

	// POST CREATE
	PostDto createPoast(PostDto postDto, Integer userId, Integer categoryId);

	// POST DELETE
	void deletePost(Integer postId);

	// POST UPDATE
	PostDto updatePost(PostDto postDto, Integer postId);

	// GET ALL POST
	PostResponce getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	// GET SINGLE POST
	PostDto getPostById(Integer postId);

	// GET ALL POST BY CATEGORY
	List<PostDto> getAllPostByCategory(Integer categoryId);

	// GET ALL POST BY USER
	List<PostDto> getAllPostByUser(Integer userId);

	// SEARCH POST
	List<PostDto> searchPost(String post);

}
