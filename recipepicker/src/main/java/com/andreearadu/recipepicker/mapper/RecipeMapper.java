package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.Recipe;

@Component
public class RecipeMapper {
	
	public RecipeDto toDto(Recipe recipe) {
		if(recipe==null) {
			throw new CustomIllegalParameterException("Recipe paramater is null");
		}
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setCategory(recipe.getCategory());
		recipeDto.setCookingTimeInMinutes(recipe.getCookingTimeInMinutes());
		recipeDto.setDescription(recipe.getDescription());
		recipeDto.setId(recipe.getId());
		recipeDto.setName(recipe.getName());
		return recipeDto;
	}

	public Recipe toEntity(RecipeDto recipeDto) {
		if(recipeDto==null) {
			throw new CustomIllegalParameterException("Recipe parmeter is null");
		}
		Recipe recipe = new Recipe();
		recipe.setCategory(recipeDto.getCategory());
		recipe.setCookingTimeInMinutes(recipeDto.getCookingTimeInMinutes());
		recipe.setDescription(recipeDto.getDescription());
		recipe.setId(recipeDto.getId());
		recipe.setName(recipeDto.getName());
	        return recipe;
	}

}
