package com.example.springjspdemo.dao;

import java.util.List;

import com.example.springjspdemo.controller.bean.User;

public interface UserDao {

	User getUserById(String userId);
	
	String createNewUser(User user);
	
	List<User> getAllUsers();
	
}
