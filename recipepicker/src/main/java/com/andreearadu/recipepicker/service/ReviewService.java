package com.andreearadu.recipepicker.service;

import java.util.Collection;

import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Stars;

public interface ReviewService {

	Collection<ReviewDto> getAllReviews();

	Collection<ReviewDto> getReviewsByRecipe(String name);

	Collection<ReviewDto> getReviewByStars(Stars stars);

	ReviewDto addReview(ReviewDto reviewDTO);

}
