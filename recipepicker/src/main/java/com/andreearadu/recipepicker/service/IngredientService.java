package com.andreearadu.recipepicker.service;


import java.util.Collection;

import com.andreearadu.recipepicker.dto.IngredientDto;

public interface IngredientService {

	Collection<IngredientDto> getAllIngredients();

	Collection<IngredientDto> getIngredients(String name);

	IngredientDto addIngredient(IngredientDto ingredientDto);

	void removeIngredient(IngredientDto ingredientDto);

}
