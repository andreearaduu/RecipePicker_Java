package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

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

	@InjectMocks
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

		Collection<RecipeDto> expectedRecipesSet = new HashSet<RecipeDto>();

		RecipeDto recipeOne = initRecipeDto(1L, "bread");
		RecipeDto recipeTwo = initRecipeDto(2L, "pasta");
		RecipeDto recipeThree = initRecipeDto(3L, "pizza");
		expectedRecipesSet.add(recipeOne);
		expectedRecipesSet.add(recipeTwo);
		expectedRecipesSet.add(recipeThree);

		when(repository.findAll())
				.thenReturn(expectedRecipesSet.stream().map(recipeMapper::toEntity).collect(Collectors.toSet()));

		Collection<RecipeDto> actualRecipesSet = service.getAllRecipes();

		assertEquals(3, actualRecipesSet.size());
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

		Recipe recipeOne = initRecipe(1L, "cheese cake",Category.BREAD,90);
		Recipe recipeTwo = initRecipe(2L, "cake",Category.PASTE,20);
		Recipe recipeThree = initRecipe(3L, "coffe cake",Category.VARIOUS,40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);
		
		when(repository.findByNameLike(name)).thenReturn(recipes);

		Collection<RecipeDto> recipesFound = service.getRecipeByNameLike(name);

		assertEquals(3, recipesFound.size());
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

		Recipe recipeOne = initRecipe(1L, "bread",category,90);
		Recipe recipeTwo = initRecipe(2L, "black bread",category,90);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
	
		when(repository.findByCategory(category)).thenReturn(recipes);

		Collection<RecipeDto> recipesFound = service.getRecipeByCatgory(category);

		assertEquals(2, recipesFound.size());

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

		Recipe recipeOne = initRecipe(1L, "bread",Category.BREAD,10);
		Recipe recipeTwo = initRecipe(2L, "cake",Category.CAKES,40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
	

		when(repository.findRecipeByCookingTimeInMinutesLessThan(cookingTime)).thenReturn(recipes);

		Collection<RecipeDto> recipesFound = service.getRecipeByCookingTimeLessThan(cookingTime);

		assertEquals(2, recipesFound.size());
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullRecipeTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addRecipe(null);

	}

	@Test
	public void addRecipeTest() {

		RecipeDto recipeDto = initRecipeDto(1L, "flour");

		when(repository.save(Mockito.any(Recipe.class))).thenReturn(recipeMapper.toEntity(recipeDto));

		RecipeDto recipeAdded = service.addRecipe(recipeDto);

		assertEquals("flour", recipeAdded.getName());
		assertEquals(1L, recipeAdded.getId());

	}

	private RecipeDto initRecipeDto(long l, String string) {

		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(l);
		recipeDto.setName(string);
		return recipeDto;
	}

	private Recipe initRecipe(long id, String name,Category category, int cookingTime) {
		Recipe recipe = new Recipe();
		recipe.setId(id);
		recipe.setName(name);
		recipe.setCategory(category);
		recipe.setCookingTimeInMinutes(cookingTime);
		return recipe;
	}
}
