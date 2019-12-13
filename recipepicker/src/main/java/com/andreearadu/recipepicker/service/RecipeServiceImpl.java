package com.andreearadu.recipepicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<Recipe> getAllRecipes() {
		return (List<Recipe>) recipeRepository.findAll();
	}

	@Override
	public Recipe getRecipeById(Long id) {
		return recipeRepository.findById(id).get();
	}

	@Override
	public List<Recipe> getRecipeByName(String name) {
		return recipeRepository.findByName(name);
	}

	@Override
	public List<Recipe> getRecipeByCatgory(Category category) {
		return recipeRepository.findByCategory(category);
	}

	@Override
	public List<Recipe> getRecipeByListIngredient(List<Ingredient> ingredients) {
		return recipeRepository.findByIngredientsIn(ingredients);
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		recipeRepository.delete(recipe);
	}
}
