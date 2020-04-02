package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<UserDto> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public UserDto getUserById(@RequestParam("id") long id) {
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/{userId}/recipes/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<RecipeDto> getFavoriteRecipesByUser(@PathVariable("userId") long userId,
			@RequestParam("recipeType") String recipeType) {
		switch (recipeType) {
		case "favorite":
			return userService.getFavoriteRecipesByUser(userId);
		case "cooked":
			return userService.getCookedRecipesByUser(userId);
		case "own":
			return userService.getRecipesOwnedByUser(userId);
		default:
			return userService.getRecipesOwnedByUser(userId);
		}
	}

	@RequestMapping(value = "/{userId}/recipe", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public RecipeDto addRecipeToUser(@PathVariable("userId") long userId, @RequestBody RecipeDto recipeDto) {
		switch (recipeDto.getRecipeType().toString()) {
		case "favorite":
			return userService.addFavoriteRecipe(recipeDto, userId);
		case "cooked":
			return userService.addCookedRecipe(recipeDto, userId);
		case "own":
			return userService.addOwnRecipe(recipeDto, userId);
		default:
			return userService.addOwnRecipe(recipeDto, userId);
		}
	}
}
