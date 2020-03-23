package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<UserDto> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public UserDto getUserById(@RequestParam("id") long id) {
		return userService.getUserById(id);
	} 
	
	@RequestMapping(value = "/user/recipes/own", method = RequestMethod.GET)
	public Collection<RecipeDto> getRecipesOwnedByUser(@RequestParam("id") long id) {
		return userService.getRecipesOwnedByUser(id);
	}
	
	@RequestMapping(value = "/user/recipes/favorite", method = RequestMethod.GET)
	public Collection<RecipeDto> getFavoriteRecipesByUser(@RequestParam("id") long id) {
		return userService.getFavoriteRecipesByUser(id);
	}

	@RequestMapping(value = "/user/recipes/cooked", method = RequestMethod.GET)
	public Collection<RecipeDto> getCookedRecipesByUser(@RequestParam("id") long id) {
		return userService.getCookedRecipesByUser(id);
	}
}
