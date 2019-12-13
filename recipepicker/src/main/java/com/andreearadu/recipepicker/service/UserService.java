package com.andreearadu.recipepicker.service;

import java.util.List;

import com.andreearadu.recipepicker.model.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserById(Long id);

	User getUserByEmail(String email);

	User saveUser(User user);

}
