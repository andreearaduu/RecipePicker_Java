package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Stars;

public interface ReviewService {

	Collection<ReviewDto> getAllReviews();

<<<<<<< HEAD
	Collection<ReviewDto> getReviewsForRecipe(String recipeName);

	Collection<ReviewDto> getReviewByStars(Stars stars);

	ReviewDto addReview(ReviewDto reviewDTO, RecipeDto recipeDto);
=======
	Collection<ReviewDto> getReviewsByRecipe(String name);

	Collection<ReviewDto> getReviewByStars(Stars stars);

	ReviewDto addReview(ReviewDto reviewDTO);
>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04

}
