package com.andreearadu.recipepicker.mapper;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Ingredient;

public class IngredientMapper {

	public IngredientDto toDto(Ingredient ingredient) {
		if (ingredient == null) {
			throw new CustomIllegalParameterException("Ingredient parameter is null");
		}

		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ingredient.getId());
		ingredientDto.setName(ingredient.getName());

		return ingredientDto;
	}

	public Ingredient toEntity(IngredientDto ingredientDto) {
		if(ingredientDto==null) {
			throw new CustomIllegalParameterException("Ingredient paramater is null");
		}

		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientDto.getId());
		ingredient.setName(ingredientDto.getName());

		return ingredient;
	}
}
