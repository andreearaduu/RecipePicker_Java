package com.andreearadu.recipepicker.service;

import java.util.Set;
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

	private UserRepository repository;
	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		super();
		this.repository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public Set<UserDto> getAllUsers() {
		return repository.findAll().stream().map(userMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		if (email == null) {
			throw new CustomIllegalParameterException("Email parameter is null");
		}
		return userMapper.toDto(repository.findByEmail(email));
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		if (userDto == null) {
			throw new CustomIllegalParameterException("User parameter is null");
		}
		return userMapper.toDto(repository.save(userMapper.toEntity(userDto)));
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		if (userDto == null) {
			throw new CustomIllegalParameterException("User parameter is null");
		}
		return userMapper.toDto(repository.save(userMapper.toEntity(userDto)));
	}

	@Override
	public Set<RecipeDto> addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto) {
		userDto.addToFavoriteRecipes(recipeDto);
		return userDto.getFavoriteRecipes();
	}

	@Override
	public Set<RecipeDto> addCookedRecipe(RecipeDto recipeDto, UserDto userDto) {

		userDto.addToCookedRecipes(recipeDto);
		return userDto.getCookedRecipes();

	}

}
