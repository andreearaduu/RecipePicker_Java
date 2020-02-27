package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository repository;
	private final RecipeMapper recipeMapper;


	@Autowired
	public RecipeServiceImpl(RecipeRepository repository, RecipeMapper recipeMapper) {

		this.repository = repository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public Collection<RecipeDto> getAllRecipes() {
		return repository.findAll().stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipeByNameLike(String name) {
		if (name == null) {
			throw new CustomIllegalParameterException("Name parameter is null");
		}
		return repository.findByNameLike(name).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipeByCatgory(Category category) {

		if (category == null) {
			throw new CustomIllegalParameterException("Category parameter is null");
		}

		return repository.findByCategory(category).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipeByCookingTimeLessThan(int cookingTime) {
		if (cookingTime == 0) {
			throw new CustomIllegalParameterException("Cooking time parameter is zero");
		}
		return repository.findRecipeByCookingTimeInMinutesLessThan(cookingTime).stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());

	}

	@Override
	public RecipeDto addRecipe(RecipeDto recipeDto) {
		if (recipeDto == null) {
			throw new CustomIllegalParameterException("Recipe parameter is null");
		}
		return recipeMapper.toDto(repository.save(recipeMapper.toEntity(recipeDto)));

	}

}
