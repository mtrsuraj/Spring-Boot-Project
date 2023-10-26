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

import com.techinfoguider.post.payload.ResponceApi;
import com.techinfoguider.post.payload.UserDto;
import com.techinfoguider.post.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//CREATE USER
	
	@PostMapping("/create-users")
	private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}
	
	//GET USER
	@GetMapping("/")
	private ResponseEntity<List<UserDto>> getUser() {
//		List<UserDto> allUser = this.userService.getAllUser();
//		return new ResponseEntity(allUser, HttpStatus.OK);
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	//UPDATE USER
	@PutMapping("/{userId}")
	private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "userId") Integer userDtoId) {
		UserDto updateUser = this.userService.updateUser(userDto, userDtoId);
		return ResponseEntity.ok(updateUser);
	}
	
	//GETUSER BY ID
	@GetMapping("/{userId}")
	private ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Integer userId){
//		UserDto userDetails = this.userService.getUserById(userId);
//		return new ResponseEntity<>(userDetails, HttpStatus.OK);
		  return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	//DELETE USER
	@DeleteMapping("/{userId}")
	private ResponseEntity<ResponceApi> deleteUser(@PathVariable(name = "userId") Integer userId){
		this.userService.deleteUser(userId);
//		return ResponseEntity.ok(Map.of("User Deleted Successfully!", userId));
		return new ResponseEntity<ResponceApi>( new ResponceApi("user deleted successfully", true), HttpStatus.OK);
	}
	
}
