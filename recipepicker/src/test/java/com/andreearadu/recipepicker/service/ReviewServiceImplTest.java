package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
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

	private ReviewServiceImpl service;

	private Collection<Review> expectedReviewSet;

	@Before
	public void setUp() {

		repository = mock(ReviewRepository.class);
		mapper = new ReviewMapper();
		service = new ReviewServiceImpl(repository, mapper);

		MockitoAnnotations.initMocks(this);

		expectedReviewSet = new HashSet<Review>();

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(90);
		recipe.setDescription("description");
		recipe.setName("bread");
		LocalDate date = LocalDate.ofYearDay(2014, 100);
		Review reviewOne = initReview(1L, "description", Stars.ONE, date, recipe);
		Review reviewTwo = initReview(2L, "description", Stars.ONE, date, recipe);
		Review reviewThree = initReview(3L, "description", Stars.ONE, date, recipe);
		expectedReviewSet.add(reviewOne);
		expectedReviewSet.add(reviewTwo);
		expectedReviewSet.add(reviewThree);

	}

	@Test
	public void getAllReviewsTest() {

		when(repository.findAll()).thenReturn(expectedReviewSet);

		assertEquals(3, service.getAllReviews().size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getReviewsByNullRecipeTest() {

		when(repository.findByRecipeName(null)).thenThrow(CustomIllegalParameterException.class);
		service.getReviewsForRecipe(null);
	}

	@Test
	public void getReviewsByRecipeTest() {

		when(repository.findByRecipeName("bread")).thenReturn(expectedReviewSet);

		assertEquals(3, service.getReviewsForRecipe("bread").size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getReviewsByNullStarsTest() {

		when(repository.findByStars(null)).thenThrow(CustomIllegalParameterException.class);
		service.getReviewByStars(null);
	}

	@Test
	public void getReviewsByStarsTest() {

		when(repository.findByStars(Stars.ONE)).thenReturn(expectedReviewSet);

		assertEquals(3, service.getReviewByStars(Stars.ONE).size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullReviewTest() {
		RecipeDto recipe = new RecipeDto();
		recipe.setId(1L);
		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addReview(null, recipe);
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addReviewToNullRecipeTest() {
		RecipeDto recipe = null;
		Review review = initReview(1L, "description", Stars.ONE, LocalDate.ofYearDay(2014, 100), null);
		when(repository.save(review)).thenThrow(CustomIllegalParameterException.class);
		service.addReview(mapper.toDto(review), recipe);
	}

	@Test
	public void addReviewTest() {

		RecipeMapper recipeMapper = new RecipeMapper();
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Review review = initReview(1L, "description", Stars.ONE, LocalDate.ofYearDay(2014, 100), recipe);

		when(repository.save(Mockito.any(Review.class))).thenReturn(review);

		assertEquals(1L, service.addReview(mapper.toDto(review), recipeMapper.toDto(recipe)).getId());
		assertEquals("description",
				service.addReview(mapper.toDto(review), recipeMapper.toDto(recipe)).getDescription());

	}

	private Review initReview(long l, String description, Stars stars, LocalDate date, Recipe recipe) {
		Review review = new Review();
		review.setId(l);
		review.setDescription(description);
		review.setStars(stars);
		review.setDate(date);
		review.setRecipe(recipe);
		return review;
	}
}
