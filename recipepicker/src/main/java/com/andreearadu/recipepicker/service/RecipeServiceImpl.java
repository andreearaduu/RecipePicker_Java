package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.ReviewDto;
import com.andreearadu.recipepicker.exceptions.IlleagalIngredientNameException;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeNameException;
import com.andreearadu.recipepicker.exceptions.InvalidCookingTimeParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalCategoryParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalListIngredientException;
import com.andreearadu.recipepicker.exceptions.InvalidIngredientParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalReviewParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalStarsParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.ReviewMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Stars;
import com.andreearadu.recipepicker.repository.RecipeRepository;
import com.andreearadu.recipepicker.repository.ReviewRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeMapper recipeMapper;
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;

	private final IngredientMapper ingredientMapper;

	@Autowired
	public RecipeServiceImpl(RecipeRepository repository, RecipeMapper recipeMapper, ReviewRepository reviewRepository,
			ReviewMapper reviewMapper, IngredientMapper ingredientMapper) {

		this.recipeRepository = repository;
		this.recipeMapper = recipeMapper;
		this.reviewRepository = reviewRepository;
		this.reviewMapper = reviewMapper;
		this.ingredientMapper = ingredientMapper;

	}

	@Override
	public Collection<RecipeDto> getRecipesByListIngredients(List<IngredientDto> ingredients) {
		if (ingredients == null) {
			throw new IllegalListIngredientException("List of ingredients is null");
		}
		return recipeRepository
				.findByListIngredientsIn(
						ingredients.stream().map(ingredientMapper::toEntity).collect(Collectors.toList()))
				.stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipesByIngredientNameLike(String ingredientName) {
		if (ingredientName == null) {
			throw new IlleagalIngredientNameException("Ingredient name is null");
		}
		return recipeRepository.findByListIngredients_name(ingredientName).stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<IngredientDto> getIngredientsForRecipe(RecipeDto recipeDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe name is null");
		}
		return recipeDto.getIngredients();

	}

	@Override

	public boolean addReviewToRecipe(RecipeDto recipeDto, ReviewDto reviewDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}
		if (reviewDto == null) {
			throw new IllegalReviewParameterException("Review parameter is null");
		}
		return recipeDto.addReview(reviewDto);
	}

	@Override
	public boolean addIngredientToRecipe(IngredientDto ingredientDto, RecipeDto recipeDto) {
		if (ingredientDto == null) {
			throw new InvalidIngredientParameterException("Ingredient parameter is null");
		}
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe parameter is null");
		}

		return recipeDto.addIngredient(ingredientDto);
	}

	@Override
	public Collection<RecipeDto> getRecipesByReviewStars(Stars stars) {
		if (stars == null) {
			throw new IllegalStarsParameterException("Stars parameter is null");
		}
		return recipeRepository.findByReviewStars(stars).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
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

	@Override
	public Collection<RecipeDto> getRecipesByCategory(Category category) {

		if (category == null) {
			throw new IllegalCategoryParameterException("Category parameter is null");
		}

		return recipeRepository.findByCategory(category).stream().map(recipeMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<RecipeDto> getRecipesByCookingTimeLessThan(int cookingTime) {
		if (cookingTime == 0) {
			throw new InvalidCookingTimeParameterException("Cooking time parameter is zero");
		}
		if (cookingTime < 0) {
			throw new InvalidCookingTimeParameterException("Cooking time parameter is negative");
		}
		return recipeRepository.findByCookingTimeInMinutesLessThan(cookingTime).stream().map(recipeMapper::toDto)
				.collect(Collectors.toSet());

	}

	@Override
	public Collection<ReviewDto> getReviewsForRecipe(RecipeDto recipeDto) {
		if (recipeDto == null) {
			throw new IllegalRecipeParameterException("Recipe name is null");
		}
		return reviewRepository.findByRecipe(recipeMapper.toEntity(recipeDto)).stream().map(reviewMapper::toDto)
				.collect(Collectors.toSet());
	}

}
