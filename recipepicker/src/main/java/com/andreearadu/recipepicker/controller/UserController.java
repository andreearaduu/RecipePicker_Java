package com.andreearadu.recipepicker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<UserDto>> getAllUsers() {
		Collection<UserDto> users = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(users);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDto> getUserById(@RequestParam("id") long id) {
		UserDto userDto = userService.getUserById(id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userDto);
	}

	@RequestMapping(value = "/{userId}/recipe", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<RecipeDto>> getRecipesByUser(@PathVariable("userId") long userId,
			@RequestParam("recipeType") String recipeType) {
		Collection<RecipeDto> recipes = userService.getRecipesForUser(userId, recipeType);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(recipes);
	}

	@RequestMapping(value = "/{userId}/recipe", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<RecipeDto> addRecipeToUser(@PathVariable("userId") long userId, @RequestBody RecipeDto recipeDto) {
		RecipeDto recipe= userService.addRecipeToUser(recipeDto, userId);
		String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recipe.getId())
                .toUriString();
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header(HttpHeaders.LOCATION, location)
				.body(recipe);
	}
	@RequestMapping(value = "/{userId}/recipe", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> deleteRecipeFromUser(@PathVariable("userId") long userId, @RequestParam long recipeId) {
		 userService.removeRecipeFromUser(recipeId, userId);
		 return ResponseEntity.accepted().build();
	}
}
