package com.andreearadu.recipepicker.service;

import java.util.Set;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	Set<UserDto> getAllUsers();

	UserDto getUserByEmail(String email);

	UserDto addUser(UserDto userDTO);

	UserDto updateUser(UserDto userDTO);
	
	Set<RecipeDto> addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto);
	
	Set<RecipeDto> addCookedRecipe(RecipeDto recipeDto, UserDto userDto);

}
