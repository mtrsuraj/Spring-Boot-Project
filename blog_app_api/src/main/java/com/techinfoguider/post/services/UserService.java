package com.techinfoguider.post.services;

import java.util.List;

import com.techinfoguider.post.payload.UserDto;


public interface UserService {

	// CREATE USER
	UserDto createUser(UserDto userDto);

	// UPDATE USER
	UserDto updateUser(UserDto userDto, Integer userId);

	// GET USER BY ID
	UserDto getUserById(Integer userId);

	// GET ALL USER
	List<UserDto> getAllUser();

	// DELETE USER
	void deleteUser(Integer userId);
	
	
	
	

}
