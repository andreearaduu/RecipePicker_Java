package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
import com.andreearadu.recipepicker.exceptions.UserNotFoundException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.UserMapper;
import com.andreearadu.recipepicker.model.Recipe;

import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.RecipeRepository;
import com.andreearadu.recipepicker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final UserMapper userMapper;
	private final RecipeMapper recipeMapper;
	private final RecipeRepository recipeRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RecipeRepository recipeRepository,
			RecipeMapper recipeMapper) {

		this.repository = userRepository;
		this.userMapper = userMapper;
		this.recipeMapper = recipeMapper;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Collection<UserDto> getAll() {
		return repository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(long id) {
		return userMapper.toDto(repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
	}

	@Override
	public Collection<RecipeDto> getRecipesForUser(long id, String recipeType) {
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		switch (recipeType) {
		case "favorite":
			return user.getFavoriteRecipes().
					stream().map(recipeMapper::toDto).collect(Collectors.toSet());
		case "cooked":
			return user.getCookedRecipes().
					stream().map(recipeMapper::toDto).collect(Collectors.toSet());
		default:
			return user.getOwnRecipes().
					stream().map(recipeMapper::toDto).collect(Collectors.toSet());
		}

	}
	@Override
	public RecipeDto addRecipeToUser(RecipeDto recipeDto, long userId) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Recipe recipe = recipeMapper.toEntity(recipeDto);
		recipe.setUser(user);
		
		switch (recipe.getRecipeType()) {
		case favorite:
			user.getFavoriteRecipes().add(recipe);
		case cooked:
			user.getCookedRecipes().add(recipe);
		default:
			user.getOwnRecipes().add(recipe);
		}
        return recipeMapper.toDto(recipeRepository.save(recipe));
	}
}
