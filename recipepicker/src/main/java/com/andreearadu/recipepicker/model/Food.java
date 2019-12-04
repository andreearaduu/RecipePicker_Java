package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

enum Type {
	FAST_FOOD, BREAKFAST, LUNCH, DINNER, DESSERT
}

@Entity
@Table(name = "food")
public class Food {

	@Id
	@Column(name = "name")
	@NotNull
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Recipe> recipes;

	@Enumerated(EnumType.STRING)
	@Column(name = "foodType")
	@NotNull
	private Type foodType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Type getFoodType() {
		return foodType;
	}

	public void setFoodType(Type foodType) {
		this.foodType = foodType;
	}
	
	
	

}
