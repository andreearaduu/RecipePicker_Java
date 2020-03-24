package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	Collection<UserDto> getAll();

	UserDto getUserById(long id);

	Collection<RecipeDto> getRecipesOwnedByUser(long id);

	Collection<RecipeDto> getFavoriteRecipesByUser(long id);

	Collection<RecipeDto> getCookedRecipesByUser(long id);
}
