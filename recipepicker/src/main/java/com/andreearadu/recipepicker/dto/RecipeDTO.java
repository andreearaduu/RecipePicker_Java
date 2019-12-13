package com.andreearadu.recipepicker.dto;

import java.util.List;

import com.andreearadu.recipepicker.model.Category;



public class RecipeDTO {

	private Long id;
	private String name;
	private String description;
	private int cookingTimeInMinutes;
	private Category category;
	private List<ReviewDTO> reviews;
	private List<IngredientDTO> ingredients;
	private List<UserDTO> userFavoriteRecepies;
	private List<UserDTO> userCookedRecepies;
	
	

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public List<UserDTO> getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(List<UserDTO> userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public List<UserDTO> getUserCookedRecepies() {
		return userCookedRecepies;
	}

	public void setUserCookedRecepies(List<UserDTO> userCookedRecepies) {
		this.userCookedRecepies = userCookedRecepies;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCookingTimeInMinutes() {
		return cookingTimeInMinutes;
	}

	public void setCookingTimeInMinutes(int cookingTimeInMinutes) {
		this.cookingTimeInMinutes = cookingTimeInMinutes;
	}

}
