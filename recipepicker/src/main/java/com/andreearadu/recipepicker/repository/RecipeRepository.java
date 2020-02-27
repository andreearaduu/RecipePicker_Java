package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Category;
<<<<<<< HEAD
=======
import com.andreearadu.recipepicker.model.Ingredient;
>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04
import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Collection<Recipe> findByNameLike(String name);

	Collection<Recipe> findByCategory(Category category);

<<<<<<< HEAD
=======
	Collection<Recipe> findByIngredientsIn(Collection<Ingredient> ingredients);
	
>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04
	Collection<Recipe> findRecipeByCookingTimeInMinutesLessThan(int cookingTime);

	Collection<Recipe> findAll();

}
