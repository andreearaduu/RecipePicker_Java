package com.andreearadu.recipepicker.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

	Set<Ingredient> findIngredientByNameLike(String name);

	Set<Ingredient> findAll();

}
