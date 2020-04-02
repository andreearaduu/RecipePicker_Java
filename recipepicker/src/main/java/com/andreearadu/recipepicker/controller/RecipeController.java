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
import com.andreearadu.recipepicker.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@RequestMapping(value = "/", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Collection<RecipeDto> getAllRecipes() {
		return recipeService.getAllRecipes();
	}

	@RequestMapping(value = "/recipe", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Collection<RecipeDto> getRecipeByName(@RequestParam("name") String name) {
		return recipeService.getRecipesByNameLike(name);
	}

	@RequestMapping(value = "/{recipeId}/ingredients", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Collection<IngredientDto> getIngredientsForRecipe(@PathVariable("recipeId") long recipeId) {
		return recipeService.getIngredientsForRecipe(recipeId);
	}

	@RequestMapping(value = "/{recipeId}/reviews", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Collection<ReviewDto> getReviewsForRecipe(@PathVariable("recipeId") long recipeId) {
		return recipeService.getReviewsForRecipe(recipeId);
	}

}
