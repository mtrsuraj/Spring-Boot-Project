package com.techinfoguider.post.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4, max = 20, message = "minimum length of name should be 4")
	private String name;
	@Email
	@Size(min = 4, max = 20, message = "email more than 4 character with special symbol")
	private String email;
	@NotEmpty
	@Size(min = 4, max = 10, message = "password length should be more than 6")
	private String password;
	@NotEmpty
	@Size(min = 5, max = 100, message = "content should be more than 10")
	private String about;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
