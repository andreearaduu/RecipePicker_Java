package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Ingredient;

public class IngredientMapperTest {

	Ingredient ingredient;
	IngredientDto ingredientDto;
	IngredientMapper ingredientMapper;

	@Before
	public void setUp() {
		this.ingredientMapper = new IngredientMapper();
	}

	@Test
	public void testMappToDto() {

		initIngredient();
		IngredientDto ingredientDto = ingredientMapper.toDto(ingredient);
		assertThat(ingredientDto.getId()).isEqualTo(ingredient.getId());
		assertThat(ingredientDto.getName()).isEqualTo(ingredient.getName());

	}

	@Test
	public void testMappToEntity() {

	    initIngredientDto();
		Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
		assertThat(ingredient.getId()).isEqualTo(ingredientDto.getId());
		assertThat(ingredient.getName()).isEqualTo(ingredientDto.getName());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullIngredient() {
		ingredientMapper = new IngredientMapper();
		ingredientDto = ingredientMapper.toDto(ingredient);

	}

	private void initIngredientDto() {
		ingredientDto = new IngredientDto();
		ingredientDto.setId(2L);
		ingredientDto.setName("Rice");

	}

	private void initIngredient() {
		ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setName("Flour");

	}
}
