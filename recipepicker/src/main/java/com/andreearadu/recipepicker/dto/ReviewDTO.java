package com.andreearadu.recipepicker.dto;

import com.andreearadu.recipepicker.model.Stars;

public class ReviewDTO {

	private Long id;
	private String description;
	private Stars stars;
	private RecipeDTO recipe;
	

	public RecipeDTO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDTO recipe) {
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Stars getStars() {
		return stars;
	}

	public void setStars(Stars stars) {
		this.stars = stars;
	}

	

}
