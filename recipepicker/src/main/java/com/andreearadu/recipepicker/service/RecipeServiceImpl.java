package com.andreearadu.recipepicker.service;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.modelLayer.Category;
import com.andreearadu.recipepicker.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository repository;
	private RecipeMapper recipeMapper;
	private IngredientMapper ingredientMapper;

	@Autowired
	public RecipeServiceImpl(RecipeRepository repository, RecipeMapper recipeMapper) {

		this.repository = repository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public Set<RecipeDto> getAllRecipes() {
		return repository.findAll().stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Set<RecipeDto> getRecipeByNameLike(String name) {
		if (name == null) {
			throw new CustomIllegalParameterException("Name parameter is null");
		}
		return repository.findByNameLike(name).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Set<RecipeDto> getRecipeByCatgory(Category category) {

		if (category == null) {
			throw new CustomIllegalParameterException("Category parameter is null");
		}

		return repository.findByCategory(category).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Set<RecipeDto> getRecipeByCookingTimeLessThan(int cookingTime) {
		if (cookingTime == 0) {
			throw new CustomIllegalParameterException("Cooking time parameter is zero");
		}
		return repository.findRecipeByCookingTimeLessThan(cookingTime).stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());

	}

	@Override
	public Set<RecipeDto> getRecipeByListIngredientIn(List<IngredientDto> ingredientsDto) {

		return repository
				.findByIngredientsIn(
						ingredientsDto.stream().map(elm -> ingredientMapper.toEntity(elm)).collect(Collectors.toList()))
				.stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public RecipeDto addRecipe(RecipeDto recipeDto) {
		if (recipeDto == null) {
			throw new CustomIllegalParameterException("Recipe parameter is null");
		}
		return recipeMapper.toDto(repository.save(recipeMapper.toEntity(recipeDto)));

	}

	@Override
	public Set<ReviewDto> addReview(ReviewDto reviewDto, RecipeDto recipeDto) {

		Set<ReviewDto> reviewsDto = recipeDto.getReviews();
		reviewsDto.add(reviewDto);
		return recipeDto.getReviews();
	}

}
