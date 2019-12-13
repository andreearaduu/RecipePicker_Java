package com.andreearadu.recipepicker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	public List<Ingredient> findIngredientByName(String name);

}
