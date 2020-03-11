package com.andreearadu.recipepicker.service;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.RecipeDto;

import com.andreearadu.recipepicker.exceptions.IllegalRecipeNameException;

import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.repository.RecipeRepository;


@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeMapper recipeMapper;


	@Autowired
	public RecipeServiceImpl(RecipeRepository repository, RecipeMapper recipeMapper) {

		this.recipeRepository = repository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public Collection<RecipeDto> getAllRecipes() {
		return recipeRepository.findAll().stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipesByNameLike(String recipeName) {
		if (recipeName == null) {
			throw new IllegalRecipeNameException("Name parameter is null");
		}
		return recipeRepository.findByNameLike(recipeName).stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());
	}

}
