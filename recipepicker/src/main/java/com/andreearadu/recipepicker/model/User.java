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

}
