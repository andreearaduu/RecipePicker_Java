package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Collection<Recipe> findByNameLike(String name);

	Collection<Recipe> findByCategory(Category category);

	Collection<Recipe> findByIngredientsIn(Collection<Ingredient> ingredients);
	
	Collection<Recipe> findRecipeByCookingTimeInMinutesLessThan(int cookingTime);

	Collection<Recipe> findAll();

}
