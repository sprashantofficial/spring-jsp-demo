package com.example.springjspdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springjspdemo.constants.Constants;
import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		User user = userService.getUserByUserId(userId);
		
		if (user.getPassword().equals(password)) {
			if (user.getRoleId() == Constants.ROLE_EMPLOYEE) {
				model.put("userId", userId);
				return "user";
			}
			
			List<User> usersList = userService.getAllUsers();
			
			model.put("usersList", usersList);
			model.put("userId", userId);
			return "admin";
		}
		
		model.put("errorMsg", "Please provide the correct userid and password");
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
		String out = userService.createNewUser(user);
		
		if (out.equals(Constants.REGISTER_FAILED)) {
			model.put("errorMsg", "Some issue occured with registration");
			return "register";
		} else if (out.equals(Constants.REGISTER_FAILED_DUPLICATE_USERID)) {
			model.put("errorMsg", Constants.REGISTER_FAILED_DUPLICATE_USERID);
			return "register";
		}
		
		model.put("successMsg", "User created successfully!!");
		return "login";
	}
	
}
