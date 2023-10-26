package com.techinfoguider.post.service.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techinfoguider.post.Entity.User;
import com.techinfoguider.post.payload.UserDto;
import com.techinfoguider.post.repository.UserRepo;
import com.techinfoguider.post.services.UserService;
import com.techinfoguider.post.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.userDtoToUser(userDto);
		User saveUser = this.userRepo.save(user);
		return this.userToUserDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userDtoId) {
		User user = this.userRepo.findById(userDtoId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userDtoId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User saveUser = this.userRepo.save(user);
		UserDto updatedeUser = this.userToUserDto(saveUser);
		return updatedeUser;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		UserDto userToUserDto = this.userToUserDto(user);
		return userToUserDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAllUser = this.userRepo.findAll();
		List<UserDto> collectAllDto = findAllUser.stream().map(user -> this.userToUserDto(user))
				.collect(Collectors.toList());

		return collectAllDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);

	}

	private User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmial(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;

	}

	private UserDto userToUserDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmial());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;

	}

}
