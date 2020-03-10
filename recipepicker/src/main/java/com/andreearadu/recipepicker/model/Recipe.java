package com.andreearadu.recipepicker.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private Collection<Review> reviews;

	@ManyToMany
	private Collection<Ingredient> ingredients;

	@ManyToOne
	private User user;
	
	@ManyToMany
	private Collection<User> userFavoriteRecepies;

	@ManyToMany
	private Collection<User> userCookedRecepies;

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

	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<Review> reviews) {
		this.reviews = reviews;
	}

	public Collection<User> getUserFavoriteRecepies() {
		return userFavoriteRecepies;
	}

	public void setUserFavoriteRecepies(Collection<User> userFavoriteRecepies) {
		this.userFavoriteRecepies = userFavoriteRecepies;
	}

	public Collection<User> getUserCookedRecepies() {
		return userCookedRecepies;
	}

	public void setUserCookedRecepies(Collection<User> userCookedRecepies) {
		this.userCookedRecepies = userCookedRecepies;
	}

}
