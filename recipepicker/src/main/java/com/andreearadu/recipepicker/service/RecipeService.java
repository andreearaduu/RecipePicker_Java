package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes();

	Collection<RecipeDto> getRecipesByNameLike(String name);
	
	Collection<ReviewDto> getReviewsForRecipe(long id);

	Collection<IngredientDto> getIngredientsForRecipe(long id);
	
}
