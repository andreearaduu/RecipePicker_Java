package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.RecipeNotFoundException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeMapper recipeMapper;

	private final ReviewMapper reviewMapper;
	private final IngredientMapper ingredientMapper;

	@Autowired
	public RecipeServiceImpl(RecipeRepository repository, RecipeMapper recipeMapper, ReviewMapper reviewMapper,
			IngredientMapper ingredientMapper) {

		this.recipeRepository = repository;
		this.recipeMapper = recipeMapper;
		this.reviewMapper = reviewMapper;
		this.ingredientMapper = ingredientMapper;
		
	}

	@Override
	public Collection<RecipeDto> getAllRecipes(String recipeName, Category category,Integer startCookingTime, 
			Integer endCookingTime,Collection<String>ingredientsName){
	    return recipeRepository.getAll(recipeName, category,startCookingTime,endCookingTime,ingredientsName).
	    		stream().map(recipeMapper::toDto).collect(Collectors.toSet());
		
	}
	
	@Override
	public Collection<ReviewDto> getReviewsForRecipe(long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id)).getReviews().stream().map(reviewMapper::toDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<IngredientDto> getIngredientsForRecipe(long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id)).getIngredients().stream().map(ingredientMapper::toDto)
				.collect(Collectors.toSet());
	}

}
