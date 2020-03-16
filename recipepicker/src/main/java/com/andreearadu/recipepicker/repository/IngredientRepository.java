package com.andreearadu.recipepicker.repository;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
