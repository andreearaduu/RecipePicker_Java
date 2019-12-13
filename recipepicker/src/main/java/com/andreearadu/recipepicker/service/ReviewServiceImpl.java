package com.andreearadu.recipepicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews() {
		return (List<Review>) reviewRepository.findAll();
	}

	@Override
	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).get();
	}

	@Override
	public List<Review> getReviewsByRecipe(Recipe recipe) {
		return reviewRepository.findByRecipe(recipe);
	}

	@Override
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public void deleteReview(Review review) {
		reviewRepository.delete(review);
	}
}
