package com.andreearadu.recipepicker.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
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
	public boolean addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		if (userDto == null) {
			throw new IllegalUserParameterException("User parameter is null");
		}
		return userDto.addToFavoriteRecipes(recipeDto);

	}

	@Override
	public boolean addCookedRecipe(RecipeDto recipeDto, UserDto userDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		if (userDto == null) {
			throw new IllegalUserParameterException("User parameter is null");
		}
		return userDto.addToCookedRecipes(recipeDto);

	}

	@Override
	public boolean addOwnRecipe(RecipeDto recipeDto, UserDto userDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		if (userDto == null) {
			throw new IllegalUserParameterException("User parameter is null");
		}
		return userDto.addOwnRecipe(recipeDto);
	}

	@Override
	public UserDto register(UserDto userDto) {
		if (userDto == null) {
			throw new IllegalUserParameterException("User parameter is null");
		}
		return userMapper.toDto(repository.save(userMapper.toEntity(userDto)));
	}

	@Override
	public Collection<RecipeDto> getAllRecipesOwnByUser(UserDto userDto) {
		if (userDto == null) {
			throw new IllegalUserParameterException("User parameter is null");
		}
		return userDto.getOwnRecipes();
	}
}
