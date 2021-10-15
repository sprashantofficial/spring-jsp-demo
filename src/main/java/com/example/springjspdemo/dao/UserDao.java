package com.example.springjspdemo.dao;

import com.example.springjspdemo.controller.bean.User;

public interface UserDao {

	User getUserById(String userId);
	
}
