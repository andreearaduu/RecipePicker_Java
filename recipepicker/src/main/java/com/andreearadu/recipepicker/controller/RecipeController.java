package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<RecipeDto> getAllRecipes(@RequestParam(value = "recipeName", required = false) String recipeName,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "cookingTimeStartingWith", required = false) Integer cookingTimeStartingWith,
			@RequestParam(value = "cookingTimeEndingWith", required = false) Integer cookingTimeEndingWith,
			@RequestParam(value = "ingredientsName", required = false) Collection<String> ingredientsName) { 
		return recipeService.getAllRecipes(recipeName, category, cookingTimeStartingWith, cookingTimeEndingWith,
				ingredientsName);
	}

	@RequestMapping(value = "/{recipeId}/ingredients", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<IngredientDto> getIngredientsForRecipe(@PathVariable("recipeId") long recipeId) {
		return recipeService.getIngredientsForRecipe(recipeId);
	}

	@RequestMapping(value = "/{recipeId}/reviews", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<ReviewDto> getReviewsForRecipe(@PathVariable("recipeId") long recipeId) {
		return recipeService.getReviewsForRecipe(recipeId);
	}

}
