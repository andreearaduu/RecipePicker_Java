package com.andreearadu.recipepicker.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

	@Column(name = "name", unique = true, nullable = false)
	@NotNull
	private String name;
	
	@OneToMany
	private Collection<Review> review;
	
	@OneToMany
	private Collection<Recipe> ownRecipes;

	@ManyToMany
	private Collection<Recipe> favoriteRecipes;

	@ManyToMany
	private Collection<Recipe> cookedRecipes;

	public void addOwnRecipes(Collection<Recipe> recipes) {
		this.ownRecipes=recipes;
	}

	public void addToFavoriteRecipes(Collection<Recipe> recipes) {
		this.favoriteRecipes=recipes;
	}

	public void addToCookedRecipes(Collection<Recipe> recipes) {
		this.cookedRecipes=recipes;
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

	public Collection<Recipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(Collection<Recipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public Collection<Recipe> getCookedRecipes() {
		return cookedRecipes;
	}

	public void setCookedRecipes(Collection<Recipe> cookedRecipes) {
		this.cookedRecipes = cookedRecipes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Recipe> getOwnRecipes() {
		return ownRecipes;
	}

	public void setOwnRecipes(Collection<Recipe> ownRecipes) {
		this.ownRecipes = ownRecipes;
	}
	
}
