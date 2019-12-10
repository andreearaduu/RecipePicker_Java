package com.andreearadu.recipepicker.dto;

import java.util.List;

public class IngredientDTO {

	private Long id;
	private String name;
	private List<RecipeDTO> recipes;

	public List<RecipeDTO> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeDTO> recipes) {
		this.recipes = recipes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
