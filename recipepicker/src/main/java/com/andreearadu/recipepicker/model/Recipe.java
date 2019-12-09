package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

enum Category {
	FAST_FOOD, BREAKFAST, LUNCH, DINNER, DESSERT
}

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	@NotNull
	private String description;

	@Column(name = "time_in_minutes", nullable = false)
	@NotNull
	private int cookingTimeInMinutes;

	@Enumerated(EnumType.STRING)
	@Column(name = "foodCategory", nullable = false)
	@NotNull
	private Category category;

	@OneToMany
	private List<Review> reviews;

	@ManyToMany
	private List<Ingredient> ingredients;

	@ManyToMany
	private List<User> userFavoriteRecepies;

	@ManyToMany
	private List<User> userCookedRecepies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<User> getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(List<User> userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public List<User> getUserCookedRecepies() {
		return userCookedRecepies;
	}

	public void setUserCookedRecepies(List<User> userCookedRecepies) {
		this.userCookedRecepies = userCookedRecepies;
	}

}
