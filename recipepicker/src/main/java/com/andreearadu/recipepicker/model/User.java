package com.andreearadu.recipepicker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@Column(name = "username", unique = true)
	@NotNull
	private String username;

	@Column(name = "password")
	@NotNull
	private String password;

	@Email
	@Column(name = "email", unique = true)
	@NotNull
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Recipe> modifiedRecipes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Recipe> favoriteRecipes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Recipe> cookedRecipes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Recipe> getModifiedRecipes() {
		return modifiedRecipes;
	}

	public void setModifiedRecipes(List<Recipe> modifiedRecipes) {
		this.modifiedRecipes = modifiedRecipes;
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
