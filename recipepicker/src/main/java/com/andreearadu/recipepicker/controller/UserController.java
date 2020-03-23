package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Collection<UserDto> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public UserDto getUserById(@RequestParam("id") long id) {
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/{userId}/recipes/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Collection<RecipeDto> getFavoriteRecipesByUser(@PathVariable("userId") long userId,
			@RequestParam("recipeType") String recipeType) {

		if (recipeType.equalsIgnoreCase("favorite")) {
			return userService.getFavoriteRecipesByUser(userId);
		}
		if (recipeType.equals("cooked")) {
			return userService.getCookedRecipesByUser(userId);
		}
		if (recipeType.equals("own")) {
			return userService.getRecipesOwnedByUser(userId);
		}
		return userService.getRecipesOwnedByUser(userId);

	}
}
