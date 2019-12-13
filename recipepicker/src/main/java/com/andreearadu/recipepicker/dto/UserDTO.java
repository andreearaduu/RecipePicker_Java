package com.andreearadu.recipepicker.dto;

import java.util.List;

public class UserDTO {

	private Long id;
	private String email;
	private List<RecipeDTO> favoriteRecipes;
	private List<RecipeDTO> cookedRecipes;

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

	public List<RecipeDTO> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<RecipeDTO> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public List<RecipeDTO> getCookedRecipes() {
		return cookedRecipes;
	}

	public void setCookedRecipes(List<RecipeDTO> cookedRecipes) {
		this.cookedRecipes = cookedRecipes;
	}

	

	

}
