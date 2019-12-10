package com.andreearadu.recipepicker.repository;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
