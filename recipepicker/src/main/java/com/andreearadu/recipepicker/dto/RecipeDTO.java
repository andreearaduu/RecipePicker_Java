package com.andreearadu.recipepicker.dto;

import java.util.List;
import java.util.Set;

import com.andreearadu.recipepicker.modelLayer.Category;

public class RecipeDto {

	private Long id;
	private String name;
	private String description;
	private int cookingTimeInMinutes;
	private Category category;
	private Set<ReviewDto> reviews;
	private List<IngredientDto> ingredients;
	private List<UserDto> userFavoriteRecepies;
	private List<UserDto> userCookedRecepies;

	public Set<ReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewDto> reviews) {
		this.reviews = reviews;
	}

	public List<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}

	public List<UserDto> getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(List<UserDto> userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public List<UserDto> getUserCookedRecepies() {
		return userCookedRecepies;
	}

	public void setUserCookedRecepies(List<UserDto> userCookedRecepies) {
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
