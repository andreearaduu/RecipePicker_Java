package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Category;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes(String recipeName, Category category, Integer startCookingTime, Integer endCookingTime,Collection<String>ingredientsName);

	Collection<ReviewDto> getReviewsForRecipe(long id);

	Collection<IngredientDto> getIngredientsForRecipe(long id);

}
