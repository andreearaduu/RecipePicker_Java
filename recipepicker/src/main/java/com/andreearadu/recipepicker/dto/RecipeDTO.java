package com.andreearadu.recipepicker.dto;

import com.andreearadu.recipepicker.model.Category;

public class RecipeDto {

	private Long id;
	private String name;
	private String description;
	private int cookingTimeInMinutes;
	private Category category;
    private long userId;
    
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
