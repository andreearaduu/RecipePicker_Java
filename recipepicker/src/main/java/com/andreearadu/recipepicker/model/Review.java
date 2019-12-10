package com.andreearadu.recipepicker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

enum Stars {
	ONE, TWO, THREE, FOUR, FIVE
}

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	@NotNull
	private String description;

	@Column(name = "stars", nullable = false)
	@Enumerated(EnumType.STRING)
	private Stars stars;

	@ManyToOne
	private Recipe recipe;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Stars getStars() {
		return stars;
	}

	public void setStars(Stars stars) {
		this.stars = stars;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
