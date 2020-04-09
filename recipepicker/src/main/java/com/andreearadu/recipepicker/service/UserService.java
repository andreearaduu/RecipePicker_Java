package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	Collection<UserDto> getAll();

	UserDto getUserById(long id);

	Collection<RecipeDto> getRecipesForUser(long id, String recipeType);

	RecipeDto addRecipeToUser(RecipeDto recipeDto, long userId);

	void removeRecipeFromUser(long recipeId, long userId);
}
