package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.UserNotFoundException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.UserMapper;

import com.andreearadu.recipepicker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper userMapper;
	private final RecipeMapper recipeMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RecipeMapper recipeMapper) {

		this.repository = userRepository;
		this.userMapper = userMapper;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public Collection<UserDto> getAll() {
		return repository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(long id) {
		return userMapper.toDto(
				repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
	}

	@Override
	public Collection<RecipeDto> getRecipesOwnedByUser(long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id))
				.getOwnRecipes().stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getFavoriteRecipesByUser(long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id))
				.getFavoriteRecipes().stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getCookedRecipesByUser(long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id))
				.getCookedRecipes().stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());
	}

}
