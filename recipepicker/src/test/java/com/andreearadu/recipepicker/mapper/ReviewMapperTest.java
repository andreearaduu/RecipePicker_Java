package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;

public class ReviewMapperTest {

	Review review;
	ReviewDto reviewDto;
	ReviewMapper reviewMapper;
	Recipe recipe;
	RecipeDto recipeDto;
	LocalDate dateForEntity;
	LocalDate dateForDto;

	public void setUp() {
		this.review = new Review();
		this.reviewDto = new ReviewDto();
		this.reviewMapper = new ReviewMapper();
		this.recipe = new Recipe();
		this.recipeDto = new RecipeDto();
		this.dateForEntity = LocalDate.now();
		this.dateForDto = LocalDate.now(ZoneId.of("Asia/Kolkata"));
	}

	@Test
	public void testMappToDto() {
		setUp();
		initReview();
		ReviewDto reviewDto = reviewMapper.toDto(review);
		assertThat(reviewDto.getId()).isEqualTo(review.getId());
		assertThat(reviewDto.getDate()).isEqualTo(review.getDate());
		assertThat(reviewDto.getDescription()).isEqualTo(review.getDescription());
		assertThat(reviewDto.getRecipeDto().getId()).isEqualTo(review.getRecipe().getId());
		assertThat(reviewDto.getStars()).isEqualTo(review.getStars());

	}

	private void initReview() {
		review.setId(1L);
		review.setDate(dateForEntity);
		review.setDescription("Description of the review");
		review.setRecipe(recipe);
		review.setStars(Stars.FIVE);

	}

	@Test
	public void testMappToEntity() {
		setUp();
		initReviewDto();

		Review review = reviewMapper.toEntity(reviewDto);
		assertThat(review.getId()).isEqualTo(reviewDto.getId());
		assertThat(review.getDate().toString()).isEqualTo(reviewDto.getDate().toString());
		assertThat(review.getDescription()).isEqualTo(reviewDto.getDescription());
		assertThat(review.getRecipe().getId()).isEqualTo(reviewDto.getRecipeDto().getId());
		assertThat(review.getStars()).isEqualTo(reviewDto.getStars());
	}

	private void initReviewDto() {
		reviewDto.setId(2L);
		reviewDto.setDate(dateForDto);
		reviewDto.setDescription("Description of the review");
		reviewDto.setRecipeDto(recipeDto);
		reviewDto.setStars(Stars.FOUR);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullReviewToDto() {
		reviewMapper = new ReviewMapper();
		reviewDto = reviewMapper.toDto(review);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullReviewToEntity() {
		reviewMapper = new ReviewMapper();
		review = reviewMapper.toEntity(reviewDto);

	}
}
