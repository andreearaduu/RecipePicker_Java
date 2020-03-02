package com.andreearadu.recipepicker.dto;

import java.util.Collection;

public class UserDto {

	private Long id;
	private String email;
	private String name;
	private Collection<RecipeDto> ownRecipes;
	private Collection<RecipeDto> favoriteRecipes;
	private Collection<RecipeDto> cookedRecipes;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RecipeDto> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(Collection<RecipeDto> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public Collection<RecipeDto> getCookedRecipes() {
		return cookedRecipes;
	}

	public void setCookedRecipes(Collection<RecipeDto> cookedRecipes) {
		this.cookedRecipes = cookedRecipes;
	}

	public Collection<RecipeDto> getOwnRecipes() {
		return ownRecipes;
	}

	public void setOwnRecipes(Collection<RecipeDto> ownRecipes) {
		this.ownRecipes = ownRecipes;
	}

	public boolean addOwnRecipe(RecipeDto recipeDto) {
		return ownRecipes.add(recipeDto);
	}

	public boolean addToFavoriteRecipes(RecipeDto recipeDto) {
		return favoriteRecipes.add(recipeDto);
	}

	public boolean addToCookedRecipes(RecipeDto recipeDto) {
		return cookedRecipes.add(recipeDto);
	}

}
