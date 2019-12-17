package com.andreearadu.recipepicker.dto;


import java.util.Set;

public class UserDto {

	private Long id;
	private String email;
	private Set<RecipeDto> favoriteRecipes;
	private Set<RecipeDto> cookedRecipes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RecipeDto> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(Set<RecipeDto> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public Set<RecipeDto> getCookedRecipes() {
		return cookedRecipes;
	}

	public void setCookedRecipes(Set<RecipeDto> cookedRecipes) {
		this.cookedRecipes = cookedRecipes;
	}

	public void addToFavoriteRecipes(RecipeDto recipeDto) {
		favoriteRecipes.add(recipeDto);
	}

	public void addToCookedRecipes(RecipeDto recipeDto) {
		cookedRecipes.add(recipeDto);
	}

	

}
