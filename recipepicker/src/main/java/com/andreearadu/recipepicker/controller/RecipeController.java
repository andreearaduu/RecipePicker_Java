package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<RecipeDto> getAllRecipes() {

		return recipeService.getAllRecipes();
	}
	
	@RequestMapping(value="/recipe", method = RequestMethod.GET)
	public Collection<RecipeDto> getRecipeByName(@RequestParam("name") String name) {
		
		return recipeService.getRecipesByNameLike(name);
	}
	
}
