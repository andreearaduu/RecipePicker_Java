package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Test;


import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.model.User;

public class ReviewMapperTest {

	ReviewMapper reviewMapper;
	
	@Before
	public void setUp() {
		this.reviewMapper = new ReviewMapper();
	}

	@Test
	public void testMappToDto() {
		Review review = initReview();
		ReviewDto reviewDto = reviewMapper.toDto(review);
		assertThat(reviewDto.getId()).isEqualTo(review.getId());
		assertThat(reviewDto.getDate()).isEqualTo(review.getDate());
		assertThat(reviewDto.getDescription()).isEqualTo(review.getDescription());
		assertThat(reviewDto.getStars()).isEqualTo(review.getStars());
		assertThat(reviewDto.getIdRecipe()).isEqualTo(review.getRecipe().getId());
		assertThat(reviewDto.getIdUser()).isEqualTo(review.getUser().getId());

	}

	@Test
	public void testMappToEntity() {

		ReviewDto reviewDto = initReviewDto();

		Review review = reviewMapper.toEntity(reviewDto);
		assertThat(review.getId()).isEqualTo(reviewDto.getId());
		assertThat(review.getDate().toString()).isEqualTo(reviewDto.getDate().toString());
		assertThat(review.getDescription()).isEqualTo(reviewDto.getDescription());
		assertThat(review.getStars()).isEqualTo(reviewDto.getStars());
		assertThat(review.getUser().getId()).isEqualTo(reviewDto.getIdUser());
		assertThat(review.getRecipe().getId()).isEqualTo(reviewDto.getIdRecipe());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullReviewToDto() {
		reviewMapper.toDto(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullReviewToEntity() {
		reviewMapper.toEntity(null);
	}

	private ReviewDto initReviewDto() {
		ReviewDto reviewDto = new ReviewDto();
		LocalDate dateForDto = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		
		reviewDto.setDate(dateForDto);
		reviewDto.setDescription("Description of the review");
		reviewDto.setIdRecipe(1L);
		reviewDto.setStars(Stars.FOUR);
		reviewDto.setIdUser(1L);
		return reviewDto;
	}

	private Review initReview() {
		Review review = new Review();
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		User user = new User();
		user.setId(2L);
		LocalDate dateForEntity = LocalDate.now();

		review.setDate(dateForEntity);
		review.setDescription("Description of the review");
		review.setRecipe(recipe);
		review.setStars(Stars.FIVE);
		review.setUser(user);
		return review;
	}

}
