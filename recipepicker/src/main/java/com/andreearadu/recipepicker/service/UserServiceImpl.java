package com.andreearadu.recipepicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
