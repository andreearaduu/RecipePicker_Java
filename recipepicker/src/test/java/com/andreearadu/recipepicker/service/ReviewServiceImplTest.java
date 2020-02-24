package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;

import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.repository.ReviewRepository;

@SpringBootTest
public class ReviewServiceImplTest {

	private ReviewMapper mapper;

	@MockBean
	private ReviewRepository repository;

	@InjectMocks
	private ReviewServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(ReviewRepository.class);
		mapper = new ReviewMapper();
		service = new ReviewServiceImpl(repository, mapper);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllReviewsTest() {

		
		Collection<ReviewDto> expectedReviewSet = new HashSet<ReviewDto>();

		RecipeDto recipe = new RecipeDto();
		recipe.setId(1L);
		LocalDate date = LocalDate.ofYearDay(2014, 100);
		ReviewDto reviewOne = initReviewDto(1L, "description", Stars.ONE, date, recipe);
		ReviewDto reviewTwo = initReviewDto(2L, "description", Stars.FIVE, date, recipe);
		ReviewDto reviewThree = initReviewDto(3L, "description", Stars.FOUR, date, recipe);
		expectedReviewSet.add(reviewOne);
		expectedReviewSet.add(reviewTwo);
		expectedReviewSet.add(reviewThree);

		when(repository.findAll())
				.thenReturn(expectedReviewSet.stream().map(mapper::toEntity).collect(Collectors.toSet()));

		Collection<ReviewDto> actualReviewSet = service.getAllReviews();

		assertEquals(3, actualReviewSet.size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getReviewsByNullRecipeTest() {

		when(repository.findByRecipeName(null)).thenThrow(CustomIllegalParameterException.class);
		service.getReviewsByRecipe(null);
	}

	@Test
	public void getReviewsByRecipeTest() {

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(90);
		recipe.setDescription("description");
		recipe.setName("bread");

		Collection<Review> expectedReviewSet = new HashSet<Review>();
		LocalDate date = LocalDate.ofYearDay(2014, 100);
		Review reviewOne = initReview(1L, "description", Stars.ONE, date, recipe);
		Review reviewTwo = initReview(2L, "description", Stars.FIVE, date, recipe);
		Review reviewThree = initReview(3L, "description", Stars.FOUR, date, recipe);
		expectedReviewSet.add(reviewOne);
		expectedReviewSet.add(reviewTwo);
		expectedReviewSet.add(reviewThree);

		when(repository.findByRecipeName("bread")).thenReturn(expectedReviewSet);

		Collection<ReviewDto> setReviewDto = service.getReviewsByRecipe("bread");

		assertEquals(3, setReviewDto.size());

	}

	private Review initReview(long id, String description, Stars stars, LocalDate date, Recipe recipe) {
		Review review = new Review();
		review.setId(id);
		review.setDescription(description);
		review.setStars(stars);
		review.setDate(date);
		review.setRecipe(recipe);
		return review;
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getReviewsByNullStarsTest() {

		when(repository.findByStars(null)).thenThrow(CustomIllegalParameterException.class);
		service.getReviewByStars(null);
	}

	@Test
	public void getReviewsByStarsTest() {

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(90);
		recipe.setDescription("description");
		recipe.setName("bread");

		Collection<Review> expectedReviewSet = new HashSet<Review>();
		LocalDate date = LocalDate.ofYearDay(2014, 100);
		Review reviewOne = initReview(1L, "description 1", Stars.ONE, date, recipe);
		Review reviewTwo = initReview(2L, "description 2", Stars.ONE, date, recipe);
		expectedReviewSet.add(reviewOne);
		expectedReviewSet.add(reviewTwo);
	

		when(repository.findByStars(Stars.ONE)).thenReturn(expectedReviewSet);

		Collection<ReviewDto> setReviewDto = service.getReviewByStars(Stars.ONE);

		assertEquals(2, setReviewDto.size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullReviewTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addReview(null);
	}

	@Test
	public void addReviewTest() {

		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(1L);
		ReviewDto reviewDto = initReviewDto(1L, "description", Stars.ONE, LocalDate.ofYearDay(2014, 100), recipeDto);

		when(repository.save(Mockito.any(Review.class))).thenReturn(mapper.toEntity(reviewDto));

		ReviewDto reviewAdded = service.addReview(reviewDto);

		assertEquals(1L, reviewAdded.getId());
		assertEquals("description", reviewAdded.getDescription());

	}

	private ReviewDto initReviewDto(long l, String description, Stars stars, LocalDate date, RecipeDto recipeDto) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(l);
		reviewDto.setDescription(description);
		reviewDto.setStars(stars);
		reviewDto.setDate(date);
		reviewDto.setRecipeDto(recipeDto);
		return reviewDto;
	}
}
