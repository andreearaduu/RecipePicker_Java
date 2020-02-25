package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
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
	public Collection<UserDto> getAllUsers() {
		return repository.findAll().stream().map(userMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		if (email == null) {
			throw new CustomIllegalParameterException("Email parameter is null");
		}
		return userMapper.toDto(repository.findByEmail(email).orElseThrow(
				() -> new CustomIllegalParameterException("User with email: " + email + " was not found")));
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		if (userDto == null) {
			throw new CustomIllegalParameterException("User parameter is null");
		}
		return userMapper.toDto(repository.save(userMapper.toEntity(userDto)));
	}

	@Override
	public boolean addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto) {
		return userDto.addToFavoriteRecipes(recipeDto);

	}

	@Override
	public boolean addCookedRecipe(RecipeDto recipeDto, UserDto userDto) {
		return userDto.addToCookedRecipes(recipeDto);

	}

}
