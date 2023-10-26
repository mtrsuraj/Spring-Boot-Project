package com.techinfoguider.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techinfoguider.post.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
