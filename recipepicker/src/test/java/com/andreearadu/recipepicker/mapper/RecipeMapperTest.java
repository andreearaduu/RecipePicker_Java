package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;

public class RecipeMapperTest {

<<<<<<< HEAD
=======
	Recipe recipe;
	RecipeDto recipeDto;
>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04
	RecipeMapper recipeMapper;

	@Before
	public void setUp() {
		this.recipeMapper = new RecipeMapper();
	}

	@Test
	public void testMappToDto() {

		Recipe recipe = initRecipe();
		RecipeDto recipeDto = recipeMapper.toDto(recipe);

		assertThat(recipeDto.getId()).isEqualTo(recipe.getId());
		assertThat(recipeDto.getName()).isEqualTo(recipe.getName());
		assertThat(recipeDto.getCategory()).isEqualTo(recipe.getCategory());
		assertThat(recipeDto.getCookingTimeInMinutes()).isEqualTo(recipe.getCookingTimeInMinutes());
		assertThat(recipeDto.getDescription()).isEqualTo(recipe.getDescription());

	}

	@Test
	public void testMappToEntity() {

		RecipeDto recipeDto = initRecipeDto();
		Recipe recipe = recipeMapper.toEntity(recipeDto);

		assertThat(recipe.getId()).isEqualTo(recipeDto.getId());
		assertThat(recipe.getName()).isEqualTo(recipeDto.getName());
		assertThat(recipe.getCategory()).isEqualTo(recipeDto.getCategory());
		assertThat(recipe.getCookingTimeInMinutes()).isEqualTo(recipeDto.getCookingTimeInMinutes());
		assertThat(recipe.getDescription()).isEqualTo(recipeDto.getDescription());
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredientToDto() {
		Recipe recipe = null;
		recipeMapper.toDto(recipe);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredientToEntity() {
		RecipeDto recipeDto = null;
		recipeMapper.toEntity(recipeDto);

	}

	private RecipeDto initRecipeDto() {

		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(2L);
		recipeDto.setName("Pasta");
		recipeDto.setCategory(Category.PASTE);
		recipeDto.setCookingTimeInMinutes(20);
		recipeDto.setDescription("description of the recepie");
		return recipeDto;

	}

	private Recipe initRecipe() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setName("Bread");
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(50);
		recipe.setDescription("description of the recepie");
		return recipe;
	}
}
