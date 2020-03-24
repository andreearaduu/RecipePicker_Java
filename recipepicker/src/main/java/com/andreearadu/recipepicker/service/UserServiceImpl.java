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
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RecipeRepository recipeRepository,RecipeMapper recipeMapper) {

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

    @Override
	public RecipeDto addOwnRecipe(RecipeDto recipeDto, long userId) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Recipe recipe = recipeMapper.toEntity(recipeDto);

		recipe.setUser(user);
		user.getOwnRecipes().add(recipe);

		return recipeMapper.toDto(recipeRepository.save(recipe));
	}

	@Override
	public RecipeDto addFavoriteRecipe(RecipeDto recipeDto, long userId) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Recipe recipe = recipeMapper.toEntity(recipeDto);

		recipe.setUser(user);
		user.getFavoriteRecipes().add(recipe);

		return recipeMapper.toDto(recipeRepository.save(recipe));
	}

	@Override
	public RecipeDto addCookedRecipe(RecipeDto recipeDto, long userId) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		Recipe recipe = recipeMapper.toEntity(recipeDto);

		recipe.setUser(user);
		user.getCookedRecipes().add(recipe);

		return recipeMapper.toDto(recipeRepository.save(recipe));
	}
}
