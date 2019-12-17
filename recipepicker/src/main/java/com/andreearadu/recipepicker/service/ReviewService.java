package com.andreearadu.recipepicker.service;

import java.util.Set;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.modelLayer.Stars;

public interface ReviewService {

	Set<ReviewDto> getAllReviews();

	Set<ReviewDto> getReviewsByRecipe(RecipeDto recipeDTO);

	Set<ReviewDto> getReviewByStars(Stars stars);

	ReviewDto addReview(ReviewDto reviewDTO);

	ReviewDto updateReview(ReviewDto reviewDTO);

}
