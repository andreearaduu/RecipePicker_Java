package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Review;

@Component
public class ReviewMapper {

	private RecipeMapper recipeMapper = new RecipeMapper();

	public ReviewDto toDto(Review review) {
		if (review == null) {
			throw new CustomIllegalParameterException("Review paramater is null");
		}

		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(review.getId());
		reviewDto.setDescription(review.getDescription());
		reviewDto.setStars(review.getStars());
		reviewDto.setDate(review.getDate());
		reviewDto.setRecipeDto(recipeMapper.toDto(review.getRecipe()));
		return reviewDto;
	}

	public Review toEntity(ReviewDto reviewDto) {
		if (reviewDto == null) {
			throw new CustomIllegalParameterException("Review paramater is null");
		}
		Review review = new Review();
		review.setId(reviewDto.getId());
		review.setDate(reviewDto.getDate());
		review.setDescription(reviewDto.getDescription());
		review.setStars(reviewDto.getStars());
		review.setRecipe(recipeMapper.toEntity(reviewDto.getRecipeDto()));
		return review;

	}

}
