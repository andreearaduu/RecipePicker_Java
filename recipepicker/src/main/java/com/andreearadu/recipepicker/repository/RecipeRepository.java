package com.andreearadu.recipepicker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	public List<Recipe> findByName(String name);

	public List<Recipe> findByCategory(Category category);

	public List<Recipe> findByIngredientsIn(List<Ingredient> ingredients);

}
