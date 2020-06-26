package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.User;
import com.app.services.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
  
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String hello()
	{
		return "welcome to restcontroller";
	}
	
	@GetMapping("/saveuser")
	public String saveUser(
			@RequestParam String username,@RequestParam String firstname,
			@RequestParam String lastname,@RequestParam String password,
			@RequestParam int age) {
	     
	        User user= new User(username, firstname, lastname, password, age);
	        service.saveMyuser(user);
		    return "User Saved";
		
	}
}
