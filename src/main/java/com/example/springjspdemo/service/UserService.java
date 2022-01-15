package com.example.springjspdemo.service;

import java.util.List;

import com.example.springjspdemo.controller.bean.User;

public interface UserService {

	User getUserByUserId(String userId);
	
	String createNewUser(User user);
	
	List<User> getAllUsers();
	
}
