package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;

public class RecipeMapperTest {

	Recipe recipe;
	RecipeDto recipeDto;
	RecipeMapper recipeMapper;

	public void setUp() {
		this.recipe = new Recipe();
		this.recipeDto = new RecipeDto();
		this.recipeMapper = new RecipeMapper();
	}

	@Test
	public void testMappToDto() {
		setUp();
		initRecipe();
		RecipeDto recipeDto = recipeMapper.toDto(recipe);
		assertThat(recipeDto.getId()).isEqualTo(recipe.getId());
		assertThat(recipeDto.getName()).isEqualTo(recipe.getName());
		assertThat(recipeDto.getCategory()).isEqualTo(recipe.getCategory());
		assertThat(recipeDto.getCookingTimeInMinutes()).isEqualTo(recipe.getCookingTimeInMinutes());
		assertThat(recipeDto.getDescription()).isEqualTo(recipe.getDescription());

	}

	private void initRecipe() {
		recipe.setId(1L);
		recipe.setName("Bread");
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(50);
		recipe.setDescription("description of the recepie");

	}

	@Test
	public void testMappToEntity() {
		setUp();
		initRecipeDto();

		Recipe recipe = recipeMapper.toEntity(recipeDto);
		assertThat(recipe.getId()).isEqualTo(recipeDto.getId());
		assertThat(recipe.getName()).isEqualTo(recipeDto.getName());
		assertThat(recipe.getCategory()).isEqualTo(recipeDto.getCategory());
		assertThat(recipe.getCookingTimeInMinutes()).isEqualTo(recipeDto.getCookingTimeInMinutes());
		assertThat(recipe.getDescription()).isEqualTo(recipeDto.getDescription());
	}

	private void initRecipeDto() {
		recipeDto.setId(2L);
		recipeDto.setName("Pasta");
		recipeDto.setCategory(Category.PASTE);
		recipeDto.setCookingTimeInMinutes(20);
		recipeDto.setDescription("description of the recepie");

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredientToDto() {
		recipeMapper = new RecipeMapper();
		recipeDto = recipeMapper.toDto(recipe);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredientToEntity() {
		recipeMapper = new RecipeMapper();
		recipe = recipeMapper.toEntity(recipeDto);

	}

}
