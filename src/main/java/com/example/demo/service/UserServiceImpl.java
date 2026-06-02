package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/*
 *
 * @author Valenciano
 * 2 jun 2026
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUser(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
