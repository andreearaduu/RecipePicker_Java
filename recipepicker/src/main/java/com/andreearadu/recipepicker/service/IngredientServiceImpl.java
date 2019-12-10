package com.andreearadu.recipepicker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public List<Ingredient> getAllIngredients() {
		return (List<Ingredient>) ingredientRepository.findAll();
	}

	@Override
	public Ingredient getIngredientById(Long id) {
		return ingredientRepository.findById(id).get();
	}

	@Override
	public List<Ingredient> getIngredientByName(String name) {
		return ingredientRepository.findIngredientByName(name);
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Override
	public void deleteIngredient(Ingredient ingredient) {
		ingredientRepository.delete(ingredient);
	}

}
