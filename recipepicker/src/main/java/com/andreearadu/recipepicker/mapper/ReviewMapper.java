package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Review;

@Component
public class ReviewMapper {

	public ReviewDto toDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("Review paramater is null");
		}
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(review.getId());
		reviewDto.setDescription(review.getDescription());
		reviewDto.setStars(review.getStars());
		reviewDto.setDate(review.getDate());
		reviewDto.setIdRecipe(review.getRecipe().getId());
		reviewDto.setIdUser(review.getUser().getId());
		return reviewDto;
	}

	public Review toEntity(ReviewDto reviewDto) {
		if (reviewDto == null) {
			throw new IllegalArgumentException("Review paramater is null");
		}
		Review review = new Review();
		review.setId(reviewDto.getId());
		review.setDate(reviewDto.getDate());
		review.setDescription(reviewDto.getDescription());
		review.setStars(reviewDto.getStars());
		review.getRecipe().setId(reviewDto.getIdRecipe());
		review.getUser().setId(reviewDto.getIdUser());
		return review;

	}

}
