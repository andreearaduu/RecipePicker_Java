package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;

public class RecipeMapperTest {

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

	@Test(expected = IllegalRecipeParameterException.class)
	public void testNullRecipeToDto() {
		Recipe recipe = null;
		recipeMapper.toDto(recipe);

	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void testNullRecipeToEntity() {
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
