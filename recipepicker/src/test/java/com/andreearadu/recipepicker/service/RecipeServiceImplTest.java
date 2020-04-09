package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.exceptions.RecipeNotFoundException;
import com.andreearadu.recipepicker.filter.RecipeFilter;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.RecipeRepository;

public class RecipeServiceImplTest {

	private RecipeRepository repository;

	private RecipeMapper recipeMapper;
	private ReviewMapper reviewMapper;
	private IngredientMapper ingredientMapper;

	private RecipeServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(RecipeRepository.class);
		recipeMapper = new RecipeMapper();
		ingredientMapper = new IngredientMapper();
		reviewMapper=new ReviewMapper();
		service = new RecipeServiceImpl( repository,  recipeMapper,  reviewMapper,
				 ingredientMapper);

		List<Recipe> recipes = new ArrayList<>();
		Recipe recipeOne = initRecipe("cheese cake", Category.CAKES, 50,1L);
		Recipe recipeTwo = initRecipe("cake", Category.CAKES, 20,2L);
		Recipe recipeThree = initRecipe("coffe cake", Category.CAKES, 40,3L);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);

		Collection<Review> reviews = new HashSet<>();
		Review review1 = initReview(1L, 1L,LocalDate.now(), "description", Stars.ONE);
		Review review2 = initReview(1L, 2L,LocalDate.now(), "description", Stars.TWO);
		reviews.add(review2);
		reviews.add(review1);
		recipeOne.setReviews(reviews);

		Collection<Ingredient> ingredients = new HashSet<>();
		Ingredient ingredientOne = new Ingredient();
		ingredientOne.setName("sweet cheese");
		ingredients.add(ingredientOne);
		recipeOne.setIngredients(ingredients);
		
		List<Recipe> recipesWithIngredientsNameLike = new ArrayList<>();
		recipesWithIngredientsNameLike.add(recipeOne);
		Collection<String> ingredientsName=new HashSet<>();
		ingredientsName.add("sweet cheese");
		
		when(repository.getAll(null,Category.CAKES,null,null,null)).thenReturn(recipes);
		when(repository.getAll("cake",null,null,null,null)).thenReturn(recipes);
		when(repository.getAll(null,null,20,null,null)).thenReturn(recipes);
		when(repository.getAll(null,null,null,60,null)).thenReturn(recipes);
		when(repository.getAll(null,null,null,null,ingredientsName)).thenReturn(recipesWithIngredientsNameLike);
		when(repository.findById(1L)).thenReturn(Optional.of(recipeOne));
		when(repository.findById(9L)).thenThrow(RecipeNotFoundException.class);
	}

	@Test
	public void getAllRecipesFromCategoryTest() {
		RecipeFilter filterForCategory = new RecipeFilter(new HashMap<>());
		filterForCategory.setCategory(Category.CAKES);
		assertEquals(3, service.getAllRecipes(filterForCategory).size());
	}
	@Test
	public void getAllRecipesByNameLikeTest() {
		RecipeFilter filterForRecipeName = new RecipeFilter(new HashMap<>());
		filterForRecipeName.setRecipeName("cake");
		assertEquals(3, service.getAllRecipes(filterForRecipeName).size());
	}
	@Test
	public void getAllRecipesByCookingTimeStartingWith() {
		RecipeFilter filterForCookingTimeStartingWith = new RecipeFilter(new HashMap<>());
		filterForCookingTimeStartingWith.setStartCookingTime(20);
		assertEquals(3, service.getAllRecipes(filterForCookingTimeStartingWith).size());
	}
	public void getAllRecipesByCookingTimeEndingWith() {
		RecipeFilter filterForCookingTimeEndingWith = new RecipeFilter(new HashMap<>());
		filterForCookingTimeEndingWith.setStartCookingTime(50);
		assertEquals(3, service.getAllRecipes(filterForCookingTimeEndingWith).size());
	}

	public void getAllRecipesByrIngredientsName() {
		Collection<String> ingredientsName=new HashSet<>();
		ingredientsName.add("sweet cheese");
		RecipeFilter filterForIngredientsName = new RecipeFilter(new HashMap<>());
		filterForIngredientsName.setIngredientsName(ingredientsName);
		assertEquals(3, service.getAllRecipes(filterForIngredientsName).size());
	}
	@Test
	public void getReviewsForRecipeTest() {
		assertEquals(2, service.getReviewsForRecipe(1).size());
	}
	
	@Test(expected = RecipeNotFoundException.class)
	public void getReviewsForRecipeNotFoundTest() {
		service.getReviewsForRecipe(9);
	}
	
	@Test
	public void getIngredientsForRecipeTest() {
		assertEquals(1, service.getIngredientsForRecipe(1).size());
	}
	
	@Test(expected = RecipeNotFoundException.class)
	public void getIngredientsForRecipeNotFoundTest() {
		service.getIngredientsForRecipe(9);
	}
	
	private Recipe initRecipe(String name, Category category, int cookingTime,long idUser) {
		Recipe recipe = new Recipe();
		User user=new User();

		recipe.setName(name);
		recipe.setCategory(category);
		recipe.setCookingTimeInMinutes(cookingTime);
		user.setId(idUser);
		recipe.setUser(user);
		return recipe;
	}
	
	private Review initReview(long recipeId,long userId,LocalDate date,String description,Stars stars) {
		Review review = new Review();
		Recipe recipe = new Recipe();
		recipe.setId(recipeId);
		User user = new User();
		user.setId(userId);
		
		review.setDate(date);
		review.setDescription(description);
		review.setRecipe(recipe);
		review.setStars(stars);
		review.setUser(user);
		return review;
	}
}
