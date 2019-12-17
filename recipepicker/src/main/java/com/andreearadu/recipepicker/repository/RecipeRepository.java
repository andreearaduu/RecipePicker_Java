package com.andreearadu.recipepicker.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.modelLayer.Category;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Optional<Recipe> findByNameLike(String name);

	Optional<Recipe> findByCategory(Category category);

	Optional<Recipe> findByIngredientsIn(List<Ingredient> ingredients);
	
	Optional<Recipe> findRecipeByCookingTimeLessThan(int cookingTime);


	Set<Recipe> findAll();

}
