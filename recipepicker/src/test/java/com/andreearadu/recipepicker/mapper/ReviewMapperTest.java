package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;

public class ReviewMapperTest {

	ReviewMapper reviewMapper;
	Recipe recipe;
	RecipeDto recipeDto;
	LocalDate dateForEntity;
	LocalDate dateForDto;

	@Before
	public void setUp() {

		this.reviewMapper = new ReviewMapper();
		this.recipe = new Recipe();
		this.recipeDto = new RecipeDto();
		this.dateForEntity = LocalDate.now();
		this.dateForDto = LocalDate.now(ZoneId.of("Asia/Kolkata"));
	}

	@Test
	public void testMappToDto() {
		Review review = initReview();
		ReviewDto reviewDto = reviewMapper.toDto(review);
		assertThat(reviewDto.getId()).isEqualTo(review.getId());
		assertThat(reviewDto.getDate()).isEqualTo(review.getDate());
		assertThat(reviewDto.getDescription()).isEqualTo(review.getDescription());
		assertThat(reviewDto.getRecipeDto().getId()).isEqualTo(review.getRecipe().getId());
		assertThat(reviewDto.getStars()).isEqualTo(review.getStars());

	}

	@Test
	public void testMappToEntity() {

		ReviewDto reviewDto = initReviewDto();

		Review review = reviewMapper.toEntity(reviewDto);
		assertThat(review.getId()).isEqualTo(reviewDto.getId());
		assertThat(review.getDate().toString()).isEqualTo(reviewDto.getDate().toString());
		assertThat(review.getDescription()).isEqualTo(reviewDto.getDescription());
		assertThat(review.getRecipe().getId()).isEqualTo(reviewDto.getRecipeDto().getId());
		assertThat(review.getStars()).isEqualTo(reviewDto.getStars());
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullReviewToDto() {
		Review review=null;
		reviewMapper.toDto(review);
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullReviewToEntity() {
		ReviewDto reviewDto=null;
		reviewMapper.toEntity(reviewDto);
	}

	private ReviewDto initReviewDto() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(2L);
		reviewDto.setDate(dateForDto);
		reviewDto.setDescription("Description of the review");
		reviewDto.setRecipeDto(recipeDto);
		reviewDto.setStars(Stars.FOUR);
		return reviewDto;
	}

	private Review initReview() {
		Review review = new Review();
		review.setId(1L);
		review.setDate(dateForEntity);
		review.setDescription("Description of the review");
		review.setRecipe(recipe);
		review.setStars(Stars.FIVE);
		return review;
	}

}
