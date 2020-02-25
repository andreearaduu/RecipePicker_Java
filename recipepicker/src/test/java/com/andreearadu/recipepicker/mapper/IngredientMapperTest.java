package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Ingredient;

public class IngredientMapperTest {

	IngredientMapper ingredientMapper;

	@Before
	public void setUp() {
		this.ingredientMapper = new IngredientMapper();
	}

	@Test
	public void testMappToDto() {
		Ingredient ingredient = initIngredient();
		IngredientDto ingredientDto = ingredientMapper.toDto(ingredient);

		assertThat(ingredientDto.getId()).isEqualTo(ingredient.getId());
		assertThat(ingredientDto.getName()).isEqualTo(ingredient.getName());

	}

	@Test
	public void testMappToEntity() {
		IngredientDto ingredientDto = initIngredientDto();
		Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
		assertThat(ingredient.getId()).isEqualTo(ingredientDto.getId());
		assertThat(ingredient.getName()).isEqualTo(ingredientDto.getName());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredient() {

		Ingredient ingredient = null;
		ingredientMapper.toDto(ingredient);

	}

	private IngredientDto initIngredientDto() {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(2L);
		ingredientDto.setName("Rice");
		return ingredientDto;

	}

	private Ingredient initIngredient() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setName("Flour");
		return ingredient;

	}
}
