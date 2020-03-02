package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	UserDto register(UserDto userDTO);

	Collection<RecipeDto> getAllRecipesOwnByUser(UserDto userDto);

	boolean addFavoriteRecipe(RecipeDto recipeDto, UserDto userDto);

	boolean addCookedRecipe(RecipeDto recipeDto, UserDto userDto);

	boolean addOwnRecipe(RecipeDto recipeDto, UserDto userDto);

}
