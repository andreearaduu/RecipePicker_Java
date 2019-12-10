package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "password", nullable = false)
	@NotNull
	private String password;

	@Email
	@Column(name = "email", unique = true, nullable = false)
	@NotNull
	private String email;

	@ManyToMany
	private List<Recipe> favoriteRecipes;

	@ManyToMany
	private List<Recipe> cookedRecipes;

	public void addToFavoriteRecipes(Recipe recipe) {
		favoriteRecipes.add(recipe);
	}

	public void addToCookedRecipes(Recipe recipe) {
		cookedRecipes.add(recipe);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public List<Recipe> getCookedRecipes() {
		return cookedRecipes;
	}

	public void setCookedRecipes(List<Recipe> cookedRecipes) {
		this.cookedRecipes = cookedRecipes;
	}

}
