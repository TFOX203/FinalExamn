/**
 * 
 */
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

/*
 *
 * @author Valenciano
 * 27 may 2026
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//Dependency injection
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String test() {
		return "hello";
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1));
		users.add(new User(2));
		return users;
	}

}
