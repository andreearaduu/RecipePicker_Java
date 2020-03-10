package com.andreearadu.recipepicker.dto;

import java.util.Collection;
import java.util.List;

import com.andreearadu.recipepicker.model.Category;

public class RecipeDto {

	private Long id;
	private String name;
	private String description;
	private int cookingTimeInMinutes;
	private Category category;
	private Collection<ReviewDto> reviews;
	private Collection<IngredientDto> ingredients;

	private Collection<UserDto> userFavoriteRecepies;
	private Collection<UserDto> userCookedRecepies;

	public boolean addIngredient(IngredientDto ingredientDto) {
		return ingredients.add(ingredientDto);
	}

	public boolean addReview(ReviewDto reviewDto) {
		return reviews.add(reviewDto);
	}

	public Collection<ReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<ReviewDto> reviews) {
		this.reviews = reviews;
	}

	public Collection<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}

	public Collection<UserDto> getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(List<UserDto> userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public Collection<UserDto> getUserCookedRecepies() {
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
