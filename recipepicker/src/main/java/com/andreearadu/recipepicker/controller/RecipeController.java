package com.andreearadu.recipepicker.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.filter.RecipeFilter;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<RecipeDto>> getAllRecipes(
			@RequestParam(value = "recipeName", required = false) String recipeName,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "cookingTimeStartingWith", required = false) Integer cookingTimeStartingWith,
			@RequestParam(value = "cookingTimeEndingWith", required = false) Integer cookingTimeEndingWith,
			@RequestParam(value = "ingredientsName", required = false) Collection<String> ingredientsName) {
		RecipeFilter filter = new RecipeFilter(new HashMap<>());
		filter.setRecipeName(recipeName);
		filter.setCategory(category);
		filter.setStartCookingTime(cookingTimeStartingWith);
		filter.setEndCookingTime(cookingTimeEndingWith);
		filter.setIngredientsName(ingredientsName);

		Collection<RecipeDto> recipes = recipeService.getAllRecipes(filter);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recipes);
	}

	@RequestMapping(value = "/{recipeId}/ingredients", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<IngredientDto>> getIngredientsForRecipe(@PathVariable("recipeId") long recipeId) {
		Collection<IngredientDto> ingredients = recipeService.getIngredientsForRecipe(recipeId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(ingredients);
	}

	@RequestMapping(value = "/{recipeId}/reviews", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<ReviewDto>> getReviewsForRecipe(@PathVariable("recipeId") long recipeId) {
		Collection<ReviewDto> reviews = recipeService.getReviewsForRecipe(recipeId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(reviews);
	}

}
