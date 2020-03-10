package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.IlleagalIngredientNameException;
import com.andreearadu.recipepicker.exceptions.IllegalCategoryParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalListIngredientException;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeNameException;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalReviewParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalStarsParameterException;

import com.andreearadu.recipepicker.exceptions.InvalidCookingTimeParameterException;
import com.andreearadu.recipepicker.exceptions.InvalidIngredientParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.repository.RecipeRepository;
import com.andreearadu.recipepicker.repository.ReviewRepository;

public class RecipeServiceImplTest {

	private RecipeRepository recipeRepository;
	private ReviewRepository reviewRepository;

	private RecipeMapper recipeMapper;
	private ReviewMapper reviewMapper;
	private IngredientMapper ingredientMapper;

	private RecipeServiceImpl service;

	@Before
	public void setUp() {

		recipeRepository = mock(RecipeRepository.class);
		reviewRepository = mock(ReviewRepository.class);
		recipeMapper = new RecipeMapper();
		reviewMapper = new ReviewMapper();
		ingredientMapper = new IngredientMapper();
		service = new RecipeServiceImpl(recipeRepository, recipeMapper, reviewRepository, reviewMapper,
				ingredientMapper);

		Collection<Recipe> recipes = new HashSet<>();
		Recipe recipeOne = initRecipe("cheese cake", Category.CAKES, 50);
		Recipe recipeTwo = initRecipe("cake", Category.CAKES, 20);
		Recipe recipeThree = initRecipe("coffe cake", Category.CAKES, 40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);

		Collection<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setStars(Stars.FIVE);
		Review review2 = new Review();
		review2.setStars(Stars.FIVE);
		reviews.add(review1);
		reviews.add(review2);
		recipeOne.setReviews(reviews);
		recipeTwo.setReviews(reviews);
		recipeThree.setReviews(reviews);

		Collection<Ingredient> ingredients = new HashSet<>();
		Ingredient ingredientOne = new Ingredient();
		ingredientOne.setName("sweet cheese");
		ingredients.add(ingredientOne);
		recipeOne.setIngredients(ingredients);
		recipeTwo.setIngredients(ingredients);
		recipeThree.setIngredients(ingredients);

		when(recipeRepository.findAll()).thenReturn(recipes);
		when(recipeRepository.findByCookingTimeInMinutesLessThan(90)).thenReturn(recipes);
		when(recipeRepository.findByNameLike("cake")).thenReturn(recipes);
		when(recipeRepository.findByCategory(Category.CAKES)).thenReturn(recipes);
		when(reviewRepository.findByRecipe(recipeOne)).thenReturn(reviews);
		when(recipeRepository.findByReviewStars(Stars.FIVE)).thenReturn(recipes);
		when(recipeRepository.findByListIngredients_name("cheese")).thenReturn(recipes);

		when(recipeRepository.findByCookingTimeInMinutesLessThan(0))
				.thenThrow(InvalidCookingTimeParameterException.class);
		when(recipeRepository.findByCookingTimeInMinutesLessThan(-1))
				.thenThrow(InvalidCookingTimeParameterException.class);
		when(recipeRepository.findByNameLike(null)).thenThrow(IllegalRecipeNameException.class);
		when(recipeRepository.findByCategory(null)).thenThrow(IllegalCategoryParameterException.class);
		when(reviewRepository.findByRecipe(null)).thenThrow(IllegalRecipeParameterException.class);
		when(recipeRepository.findByReviewStars(null)).thenThrow(IllegalStarsParameterException.class);
		when(recipeRepository.findByListIngredientsIn(null)).thenThrow(IllegalListIngredientException.class);
		when(recipeRepository.findByListIngredients_name(null)).thenThrow(IlleagalIngredientNameException.class);
	}

	@Test
	public void getAllRecipesTest() {
		assertEquals(3, service.getAllRecipes().size());
	}

	@Test(expected = IllegalRecipeNameException.class)
	public void getRecipeByNullNameLikeTest() {
		service.getRecipesByNameLike(null);
	}

	@Test
	public void getRecipeByNameLikeTest() {
		assertEquals(3, service.getRecipesByNameLike("cake").size());
	}

	@Test(expected = IllegalCategoryParameterException.class)
	public void getRecipeByNullCatgoryTest() {
		service.getRecipesByCategory(null);
	}

	@Test
	public void getRecipeByCategoryTest() {
		assertEquals(3, service.getRecipesByCategory(Category.CAKES).size());
	}

	@Test(expected = InvalidCookingTimeParameterException.class)
	public void getRecipesByNullCookingTimeLessThanTest() {
		service.getRecipesByCookingTimeLessThan(0);
	}

	@Test(expected = InvalidCookingTimeParameterException.class)
	public void getRecipesByNegativeCookingTimeLessThanTest() {
		service.getRecipesByCookingTimeLessThan(-1);
	}

	@Test
	public void getRecipesByCookingTimeLessThanTest() {
		assertEquals(3, service.getRecipesByCookingTimeLessThan(90).size());
	}

	@Test
	public void getRecipesByListIngredientsTest() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient ingredientOne = new Ingredient();
		Ingredient ingredientTwo = new Ingredient();
		ingredients.add(ingredientOne);
		ingredients.add(ingredientTwo);

		Collection<Recipe> recipes = new HashSet<>();
		Recipe recipeOne = new Recipe();
		recipeOne.setIngredients(ingredients);
		Recipe recipeTwo = new Recipe();
		recipeTwo.setIngredients(ingredients);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);

		when(recipeRepository.findByListIngredientsIn(ingredients)).thenReturn(recipes);
		assertEquals(2, service.getRecipesByListIngredients(
				ingredients.stream().map(ingredientMapper::toDto).collect(Collectors.toList())).size());
	}

	@Test(expected = IllegalListIngredientException.class)
	public void getRecipeByNullListIngredientsTest() {
		service.getRecipesByListIngredients(null);
	}

	@Test
	public void getRecipesByIngredientNameLikeTest() {
		assertEquals(3, service.getRecipesByIngredientNameLike("cheese").size());
	}

	@Test(expected = IlleagalIngredientNameException.class)
	public void getRecipeByNullIngredientNameTest() {
		service.getRecipesByIngredientNameLike(null);
	}

	@Test
	public void getRecipesByReviewStarsTest() {
		assertEquals(3, service.getRecipesByReviewStars(Stars.FIVE).size());
	}

	@Test(expected = IllegalStarsParameterException.class)
	public void getRecipeByNullStarsTest() {
		service.getRecipesByReviewStars(null);
	}

	@Test
	public void getReviewsForRecipeTest() {
		Collection<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setStars(Stars.FIVE);
		Review review2 = new Review();
		review2.setStars(Stars.FIVE);
		reviews.add(review1);
		reviews.add(review2);

		Recipe recipe = new Recipe();
		recipe.setReviews(reviews);

		when(reviewRepository.findByRecipe(recipe)).thenReturn(reviews);
		assertEquals(2, service.getReviewsForRecipe(recipeMapper.toDto(recipe)).size());
	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void getReviewsForNullRecipeTest() {
		service.getReviewsForRecipe(null);
	}

	@Test
	public void getIngredientsForRecipeTest() {
		Collection<Ingredient> ingredients = new HashSet<>();
		Ingredient ingredient1 = new Ingredient();
		Ingredient ingredient2 = new Ingredient();
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);

		Recipe recipe = new Recipe();
		recipe.setIngredients(ingredients);
		assertEquals(2, service.getIngredientsForRecipe(recipeMapper.toDto(recipe)).size());
	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void getIngredientsForNullRecipeTest() {
		service.getIngredientsForRecipe(null);
	}

	@Test
	public void addIngredientToRecipeTest() {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setName("name");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");

		assertEquals(true, service.addIngredientToRecipe(ingredientDto, recipeDto));

	}

	@Test
	public void addDuplicateFavoriteRecipeToUser() {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setName("name");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");

		service.addIngredientToRecipe(ingredientDto, recipeDto);
		assertEquals(false, service.addIngredientToRecipe(ingredientDto, recipeDto));
	}

	@Test(expected = InvalidIngredientParameterException.class)
	public void addNullIngredientToRecipeTest() {
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");
		service.addIngredientToRecipe(null, recipeDto);

	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void addIngredientToNullRecipeTest() {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setName("name");
		service.addIngredientToRecipe(ingredientDto, null);

	}

	@Test
	public void addReviewToRecipeTest() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setDescription("description");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");

		assertEquals(true, service.addReviewToRecipe(reviewDto, recipeDto));

	}

	@Test
	public void addDuplicateReviewToRecipeTest() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setDescription("description");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");

		service.addReviewToRecipe(reviewDto, recipeDto);
		assertEquals(false, service.addReviewToRecipe(reviewDto, recipeDto));

	}

	@Test(expected = IllegalReviewParameterException.class)
	public void addNullReviewToRecipeTest() {
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");
		service.addReviewToRecipe(null, recipeDto);

	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void addReviewToNullRecipeTest() {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setDescription("description");
		service.addReviewToRecipe(reviewDto, null);

	}

	private Recipe initRecipe(String name, Category category, int cookingTime) {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setCategory(category);
		recipe.setCookingTimeInMinutes(cookingTime);
		return recipe;
	}
}
