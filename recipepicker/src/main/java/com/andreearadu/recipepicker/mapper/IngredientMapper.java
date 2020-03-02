package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.model.Ingredient;

@Component
public class IngredientMapper {

	public IngredientDto toDto(Ingredient ingredient) {
		if (ingredient == null) {
			throw new IllegalArgumentException("Ingredient parameter is null");
		}
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(ingredient.getId());
		ingredientDto.setName(ingredient.getName());
		return ingredientDto;
	}

	public Ingredient toEntity(IngredientDto ingredientDto) {
		if(ingredientDto==null) {
			throw new IllegalArgumentException("Ingredient paramater is null");
		}
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientDto.getId());
		ingredient.setName(ingredientDto.getName());
		return ingredient;
	}
}
