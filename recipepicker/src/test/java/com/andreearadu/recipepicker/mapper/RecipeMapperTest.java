package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.RecipeType;
import com.andreearadu.recipepicker.model.User;

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
        assertThat(recipeDto.getUserId()).isEqualTo(recipe.getUser().getId());
        assertThat(recipeDto.getRecipeType()).isEqualTo(recipe.getRecipeType());
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
		assertThat(recipe.getRecipeType()).isEqualTo(recipeDto.getRecipeType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullRecipeToDto() {
		recipeMapper.toDto(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullRecipeToEntity() {
		recipeMapper.toEntity(null);

	}

	private RecipeDto initRecipeDto() {

		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("Pasta");
		recipeDto.setCategory(Category.PASTE);
		recipeDto.setCookingTimeInMinutes(20);
		recipeDto.setDescription("description of the recipe");
		recipeDto.setUserId(1L);
		recipeDto.setRecipeType(RecipeType.own);
		return recipeDto;

	}

	private Recipe initRecipe() {
		Recipe recipe = new Recipe();
		User user=new User();
		user.setId(2L);
		recipe.setName("Bread");
		recipe.setCategory(Category.BREAD);
		recipe.setCookingTimeInMinutes(50);
		recipe.setDescription("description of the recipe");
		recipe.setUser(user);
		recipe.setRecipeType(RecipeType.own);
		return recipe;
	}
}
