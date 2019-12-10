package com.andreearadu.recipepicker.service;

import java.util.List;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeService {

	public List<Recipe> getAllRecipes();

	public Recipe getRecipeById(Long id);

	public List<Recipe> getRecipeByName(String name);

	public List<Recipe> getRecipeByCatgory(Category category);

	public List<Recipe> getRecipeByListIngredient(List<Ingredient> ingredients);

	public Recipe saveRecipe(Recipe recipe);

	public void deleteRecipe(Recipe recipe);
}
