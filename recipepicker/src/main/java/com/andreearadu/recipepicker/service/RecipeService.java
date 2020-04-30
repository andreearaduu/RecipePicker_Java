package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.filter.RecipeFilter;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes(RecipeFilter filter);

	Collection<ReviewDto> getReviewsForRecipe(long id);

	Collection<IngredientDto> getIngredientsForRecipe(long id);

}
