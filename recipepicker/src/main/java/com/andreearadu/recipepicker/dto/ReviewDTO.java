package com.andreearadu.recipepicker.dto;

import java.time.LocalDate;

import com.andreearadu.recipepicker.model.Stars;

public class ReviewDto {

	private Long id;
	private String description;
	private Stars stars;
	private LocalDate date;
    private long idRecipe;
    private long idUser;
    
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public long getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(long idRecipe) {
		this.idRecipe = idRecipe;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

}
