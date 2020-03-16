package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.IllegalUserParameterException;
import com.andreearadu.recipepicker.mapper.UserMapper;

import com.andreearadu.recipepicker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {

		this.repository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public Collection<UserDto> getAll() {
		return repository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(long id) {
		return userMapper.toDto(repository.findById(id).orElseThrow(() -> new IllegalUserParameterException("User parameter is null")));
	}

}
