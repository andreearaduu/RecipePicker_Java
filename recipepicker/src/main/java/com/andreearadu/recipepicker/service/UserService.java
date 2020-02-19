package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	Collection<UserDto> getAllUsers();

	UserDto getUserByEmail(String email);

	UserDto addUser(UserDto userDTO);

	boolean addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto);
	
	boolean addCookedRecipe(RecipeDto recipeDto, UserDto userDto);

}
