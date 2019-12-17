package com.andreearadu.recipepicker.service;


import java.util.Set;

import com.andreearadu.recipepicker.dto.IngredientDto;

public interface IngredientService {

	Set<IngredientDto> getAllIngredients();

	Set<IngredientDto> getIngredientByNameLike(String name);

	IngredientDto addIngredient(IngredientDto ingredientDto);

	boolean removeIngredient(IngredientDto ingredientDto);

}
