package com.andreearadu.recipepicker.service;

import java.util.Collection;
import com.andreearadu.recipepicker.dto.RecipeDto;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes();

	Collection<RecipeDto> getRecipesByNameLike(String name);
}
