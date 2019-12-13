package com.andreearadu.recipepicker.service;

import java.util.List;

import com.andreearadu.recipepicker.model.Ingredient;

public interface IngredientService {

	public List<Ingredient> getAllIngredients();

	public Ingredient getIngredientById(Long id);

	public List<Ingredient> getIngredientByName(String name);

	public Ingredient saveIngredient(Ingredient ingredient);

	public void deleteIngredient(Ingredient ingredient);

}
