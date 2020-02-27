package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Ingredient;

public class IngredientMapperTest {

	Ingredient ingredient;
	IngredientDto ingredientDto;
	IngredientMapper ingredientMapper;

	public void setUp() {
		this.ingredient = new Ingredient();
		this.ingredientDto = new IngredientDto();
		ingredientMapper = new IngredientMapper();
	}

	@Test
	public void testMappToDto() {
		setUp();
		initIngredient();
		IngredientDto ingredientDto = ingredientMapper.toDto(ingredient);
		assertThat(ingredientDto.getId()).isEqualTo(ingredient.getId());
		assertThat(ingredientDto.getName()).isEqualTo(ingredient.getName());

	}

	private void initIngredient() {
		ingredient.setId(1L);
		ingredient.setName("Flour");

	}

	@Test
	public void testMappToEntity() {
		setUp();
		initIngredientDto();

		Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
		assertThat(ingredient.getId()).isEqualTo(ingredientDto.getId());
		assertThat(ingredient.getName()).isEqualTo(ingredientDto.getName());

	}

	private void initIngredientDto() {
		ingredientDto.setId(2L);
		ingredientDto.setName("Rice");

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredient() {
		ingredientMapper = new IngredientMapper();
		ingredientDto = ingredientMapper.toDto(ingredient);

	}

}
