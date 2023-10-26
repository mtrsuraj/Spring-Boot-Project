package com.techinfoguider.post.service.imple;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techinfoguider.post.Entity.Category;
import com.techinfoguider.post.Entity.Post;
import com.techinfoguider.post.Entity.User;
import com.techinfoguider.post.exceptions.ResourceNotFoundException;
import com.techinfoguider.post.payload.PostDto;
import com.techinfoguider.post.payload.PostResponce;
import com.techinfoguider.post.repository.CategoryRepo;
import com.techinfoguider.post.repository.PostRepo;
import com.techinfoguider.post.repository.UserRepo;
import com.techinfoguider.post.services.PostServices;

@Service
public class PostServiceImpl implements PostServices {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPoast(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userid", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post savePost = this.postRepo.save(post);

		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post postDetails = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		this.postRepo.delete(postDetails);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

//		Post post = this.modelMapper.map(postId, Post.class);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());

		Post savePost = this.postRepo.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
				
		Sort sort = null;
		
		if(sortDir.equalsIgnoreCase("asc")) {
			 sort= sort.by(sortBy).ascending();
		}else {
			sort = sort.by(sortBy).descending();
		}
		
		PageRequest page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(page);
		List<Post> findAll = pagePost.getContent();
		
//		List<Post> getAllPost = this.postRepo.findAll();
		List<PostDto> collectAllPost = findAll.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(collectAllPost);
		postResponce.setPageNumber(pagePost.getNumber());
		postResponce.setPageSize(pagePost.getSize());
		postResponce.setTotalElement(pagePost.getTotalElements());
		postResponce.setTotalPage(pagePost.getTotalPages());
		postResponce.setLastPage(pagePost.isLast());
		
		return postResponce;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post postDetails = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(postDetails, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category categoryDetailsId = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		  List<Post> findByCategory = this.postRepo.findByCategory(categoryDetailsId);
		List<PostDto> collectPostByCategory = findByCategory.stream()
				.map(category -> this.modelMapper.map(category, PostDto.class)).collect(Collectors.toList());
		return collectPostByCategory;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User userDetailsId = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> findByUser = this.postRepo.findByUser(userDetailsId);
		List<PostDto> collectPostByUser = findByUser.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collectPostByUser;
	}

	@Override
	public List<PostDto> searchPost(String post) {
		List<Post> findByPostNameContaining = this.postRepo.findByPostTitle(post);
		List<PostDto> collectPost = findByPostNameContaining.stream()
				.map(postKeyword -> this.modelMapper.map(postKeyword, PostDto.class)).collect(Collectors.toList());
		return collectPost;
	}

}
