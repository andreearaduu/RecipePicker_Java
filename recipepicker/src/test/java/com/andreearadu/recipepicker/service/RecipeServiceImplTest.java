package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.repository.RecipeRepository;

@SpringBootTest
public class RecipeServiceImplTest {

	private RecipeMapper recipeMapper;

	@MockBean
	private RecipeRepository repository;

	private RecipeServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(RecipeRepository.class);

		recipeMapper = new RecipeMapper();
		service = new RecipeServiceImpl(repository, recipeMapper);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllRecipesTest() {

		Collection<Recipe> expectedRecipesSet = new HashSet<Recipe>();

		Recipe recipeOne = initRecipe(1L, "bread", Category.BREAD, 90);
		Recipe recipeTwo = initRecipe(2L, "pasta", Category.PASTE, 30);
		Recipe recipeThree = initRecipe(3L, "pizza", Category.VARIOUS, 50);
		expectedRecipesSet.add(recipeOne);
		expectedRecipesSet.add(recipeTwo);
		expectedRecipesSet.add(recipeThree);

		when(repository.findAll()).thenReturn(expectedRecipesSet);

		assertEquals(3, service.getAllRecipes().size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getRecipeByNullNameLikeTest() {

		when(repository.findByNameLike(null)).thenThrow(CustomIllegalParameterException.class);

		service.getRecipeByNameLike(null);

	}

	@Test
	public void getRecipeByNameLikeTest() {

		String name = "cake";
		Collection<Recipe> recipes = new HashSet<>();

		Recipe recipeOne = initRecipe(1L, "cheese cake", Category.BREAD, 90);
		Recipe recipeTwo = initRecipe(2L, "cake", Category.PASTE, 20);
		Recipe recipeThree = initRecipe(3L, "coffe cake", Category.VARIOUS, 40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);

		when(repository.findByNameLike(name)).thenReturn(recipes);

		assertEquals(3, service.getRecipeByNameLike(name).size());
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getRecipeByNullCatgoryTest() {

		when(repository.findByCategory(null)).thenThrow(CustomIllegalParameterException.class);
		service.getRecipeByCatgory(null);
	}

	@Test
	public void getRecipeByCategoryTest() {

		Category category = Category.BREAD;

		Collection<Recipe> recipes = new HashSet<>();

		Recipe recipeOne = initRecipe(1L, "bread", category, 90);
		Recipe recipeTwo = initRecipe(2L, "black bread", category, 90);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);

		when(repository.findByCategory(category)).thenReturn(recipes);

		assertEquals(2, service.getRecipeByCatgory(category).size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getRecipeByNullCookingTimeLessThanTest() {

		when(repository.findRecipeByCookingTimeInMinutesLessThan(0)).thenThrow(CustomIllegalParameterException.class);
		service.getRecipeByCookingTimeLessThan(0);
	}

	@Test
	public void getRecipeByCookingTimeLessThanTest() {

		int cookingTime = 90;
		Collection<Recipe> recipes = new HashSet<Recipe>();

		Recipe recipeOne = initRecipe(1L, "bread", Category.BREAD, 70);
		Recipe recipeTwo = initRecipe(2L, "cake", Category.CAKES, 40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);

		when(repository.findRecipeByCookingTimeInMinutesLessThan(cookingTime)).thenReturn(recipes);

		assertEquals(2, service.getRecipeByCookingTimeLessThan(cookingTime).size());
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullRecipeTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addRecipe(null);

	}

	@Test
	public void addRecipeTest() {

		Recipe recipe = initRecipe(1L, "bread", Category.BREAD, 70);

		when(repository.save(Mockito.any(Recipe.class))).thenReturn(recipe);

		assertEquals("bread", service.addRecipe(recipeMapper.toDto(recipe)).getName());
		assertEquals(1L, service.addRecipe(recipeMapper.toDto(recipe)).getId());

	}

	private Recipe initRecipe(long id, String name, Category category, int cookingTime) {
		Recipe recipe = new Recipe();
		recipe.setId(id);
		recipe.setName(name);
		recipe.setCategory(category);
		recipe.setCookingTimeInMinutes(cookingTime);
		return recipe;
	}
}
