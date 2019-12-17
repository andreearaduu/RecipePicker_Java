package com.andreearadu.recipepicker.service;

import java.util.List;
import java.util.Set;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.modelLayer.Category;

public interface RecipeService {

	Set<RecipeDto> getAllRecipes();

	Set<RecipeDto> getRecipeByNameLike(String name);

	Set<RecipeDto> getRecipeByCatgory(Category category);

	Set<RecipeDto> getRecipeByCookingTimeLessThan(int cookingTime);

	Set<RecipeDto> getRecipeByListIngredientIn(List<IngredientDto> ingredientsDTO);

	Set<ReviewDto> addReview(ReviewDto reviewDto,RecipeDto recipeDto);

	RecipeDto addRecipe(RecipeDto recipeDTO);

}
