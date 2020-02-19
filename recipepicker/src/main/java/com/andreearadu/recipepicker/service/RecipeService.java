package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.model.Category;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes();

	Collection<RecipeDto> getRecipeByNameLike(String name);

	Collection<RecipeDto> getRecipeByCatgory(Category category);

	Collection<RecipeDto> getRecipeByCookingTimeLessThan(int cookingTime);

	RecipeDto addRecipe(RecipeDto recipeDTO);

}
