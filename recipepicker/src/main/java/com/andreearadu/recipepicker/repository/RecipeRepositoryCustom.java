package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepositoryCustom {

	Collection<Recipe> getAll(String recipeName, Category category, Integer startCookingTime, Integer endCookingTime,Collection<String>ingredientsName);
}
