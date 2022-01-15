package com.example.springjspdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.dao.UserDao;
import com.example.springjspdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByUserId(String userId) {
		User user = userDao.getUserById(userId);
		return user;
	}

	@Override
	public String createNewUser(User user) {
		return userDao.createNewUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
