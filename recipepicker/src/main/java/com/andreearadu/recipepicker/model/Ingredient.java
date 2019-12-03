package com.andreearadu.recipepicker.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@NotNull
	@Column(name = "name")
	private String name;

	@ManyToOne
	private Recipe recipe;

}
