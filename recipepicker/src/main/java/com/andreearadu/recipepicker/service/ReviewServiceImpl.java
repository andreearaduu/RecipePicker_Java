package com.andreearadu.recipepicker.service;

import java.util.Set;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.modelLayer.Stars;
import com.andreearadu.recipepicker.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository repository;
	private ReviewMapper reviewMapper;
	private RecipeMapper recipeMapper;

	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
		super();
		this.repository = reviewRepository;
		this.reviewMapper = reviewMapper;
	}

	@Override
	public Set<ReviewDto> getAllReviews() {

		return repository.findAll().stream().map(reviewMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Set<ReviewDto> getReviewsByRecipe(RecipeDto recipeDto) {
		if (recipeDto == null) {
			throw new CustomIllegalParameterException("Recipe parameter in null");
		}

		return repository.findByRecipe(recipeMapper.toEntity(recipeDto)).stream().map(reviewMapper::toDto)
				.collect(Collectors.toSet());

	}

	@Override
	public Set<ReviewDto> getReviewByStars(Stars stars) {

		if (stars == null) {
			throw new CustomIllegalParameterException("Stars parameter is null");
		}

		return repository.findByStars(stars).stream().map(reviewMapper::toDto)
				.collect(Collectors.toSet());
	}

	@Override
	public ReviewDto addReview(ReviewDto reviewDto) {

		if (reviewDto == null) {
			throw new CustomIllegalParameterException("Recipe parameter in null");
		}

		return reviewMapper.toDto(repository.save(reviewMapper.toEntity(reviewDto)));
	}

	@Override
	public ReviewDto updateReview(ReviewDto reviewDto) {

		if (reviewDto == null) {
			throw new CustomIllegalParameterException("Recipe parameter in null");
		}

		return reviewMapper.toDto(repository.save(reviewMapper.toEntity(reviewDto)));
	}

}
