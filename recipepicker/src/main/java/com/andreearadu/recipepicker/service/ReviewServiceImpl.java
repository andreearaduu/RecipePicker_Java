package com.andreearadu.recipepicker.service;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;

import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository repository;
	private final ReviewMapper reviewMapper;

	@Autowired
	public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {

		this.repository = reviewRepository;
		this.reviewMapper = reviewMapper;
	}

	@Override
	public Collection<ReviewDto> getAllReviews() {

		return repository.findAll().stream().map(reviewMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<ReviewDto> getReviewsForRecipe(String name) {
		if (name == null) {
			throw new CustomIllegalParameterException("Name parameter in null");
		}

		return repository.findByRecipeName(name).stream().map(reviewMapper::toDto).collect(Collectors.toSet());

	}

	@Override
	public Collection<ReviewDto> getReviewByStars(Stars stars) {

		if (stars == null) {
			throw new CustomIllegalParameterException("Stars parameter is null");
		}

		return repository.findByStars(stars).stream().map(reviewMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public ReviewDto addReview(ReviewDto reviewDto, RecipeDto recipeDto) {

		if (reviewDto == null) {
			throw new CustomIllegalParameterException("There is no review to add");
		}
		if(recipeDto==null) {
			throw new CustomIllegalParameterException("Recipe parameter in null");
		}
		reviewDto.setRecipeDto(recipeDto);
		return reviewMapper.toDto(repository.save(reviewMapper.toEntity(reviewDto)));
	}

}
