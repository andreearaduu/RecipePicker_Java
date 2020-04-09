package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>, RecipeRepositoryCustom {

	Collection<Recipe> findAll();
}
