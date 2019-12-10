package com.andreearadu.recipepicker.service;

import java.util.List;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;

public interface ReviewService {

	List<Review> getAllReviews();

	Review getReviewById(Long id);

	List<Review> getReviewsByRecipe(Recipe recipe);

	Review saveReview(Review review);

	void deleteReview(Review review);

}
