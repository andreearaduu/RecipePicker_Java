package com.andreearadu.recipepicker.dto;

import java.util.Collection;

public class IngredientDto {

	private Long id;
	private String name;
	private Collection<RecipeDto> recipes;

	public Collection<RecipeDto> getRecipes() {
		return recipes;
	}

	public void setRecipes(Collection<RecipeDto> recipes) {
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
