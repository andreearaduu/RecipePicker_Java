package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Id;

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

	
	@OneToMany(cascade = CascadeType.ALL)
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

}
