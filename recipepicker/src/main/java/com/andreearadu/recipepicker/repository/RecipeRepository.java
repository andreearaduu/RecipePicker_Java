package com.andreearadu.recipepicker.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Stars;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Collection<Recipe> findByNameLike(String name);

	Collection<Recipe> findByCategory(Category category);

	Collection<Recipe> findByCookingTimeInMinutesLessThan(int cookingTime);

	Collection<Recipe> findAll();

	Collection<Recipe> findByReviewStars(Stars stars);

	Collection<Recipe> findByListIngredientsIn(List<Ingredient> ingredients);

	Collection<Recipe> findByListIngredients_name(String ingredientName);
}
