package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.List;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Stars;

public interface RecipeService {

	Collection<RecipeDto> getAllRecipes();

	Collection<RecipeDto> getRecipesByNameLike(String name);

	Collection<RecipeDto> getRecipesByCategory(Category category);

	Collection<RecipeDto> getRecipesByCookingTimeLessThan(int cookingTime);

	Collection<RecipeDto> getRecipesByListIngredients(List<IngredientDto> ingredients);

	Collection<RecipeDto> getRecipesByIngredientNameLike(String ingredientName);

	Collection<RecipeDto> getRecipesByReviewStars(Stars stars);

	Collection<ReviewDto> getReviewsForRecipe(RecipeDto recipeDto);

	Collection<IngredientDto> getIngredientsForRecipe(RecipeDto recipeDto);

	boolean addIngredientToRecipe(IngredientDto ingredientDto, RecipeDto recipeDto);

	boolean addReviewToRecipe(RecipeDto recipeDto, ReviewDto reviewDto);
}
