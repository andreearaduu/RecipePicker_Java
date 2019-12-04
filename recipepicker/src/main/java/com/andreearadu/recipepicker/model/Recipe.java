package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "time")
	@NotNull
	private int timeOfCooking;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_ingredients")
	private List<Ingredient> ingredients;

	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Review> reviews;

	
	@ManyToOne
	private Food food;
	
	
	@ManyToOne
	private User userModifiedRecipes;

	@ManyToOne
	private User userFavoriteRecepies;

	@ManyToOne
	private User userCookedRecepies;

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

	public int getTimeOfCooking() {
		return timeOfCooking;
	}

	public void setTimeOfCooking(int timeOfCooking) {
		this.timeOfCooking = timeOfCooking;
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public User getUserModifiedRecipes() {
		return userModifiedRecipes;
	}

	public void setUserModifiedRecipes(User userModifiedRecipes) {
		this.userModifiedRecipes = userModifiedRecipes;
	}

	public User getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(User userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public User getUserCookedRecepies() {
		return userCookedRecepies;
	}

	public void setUserCookedRecepies(User userCookedRecepies) {
		this.userCookedRecepies = userCookedRecepies;
	}
	
	
	
	

}
