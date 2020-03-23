package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.User;

@Component
public class RecipeMapper {

	public RecipeDto toDto(Recipe recipe) {
		if(recipe==null) {
			throw new IllegalArgumentException("Recipe paramater is null");
		}
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setId(recipe.getId());
		recipeDto.setName(recipe.getName());
		recipeDto.setCategory(recipe.getCategory());
		recipeDto.setCookingTimeInMinutes(recipe.getCookingTimeInMinutes());
		recipeDto.setDescription(recipe.getDescription());
		recipeDto.setUserId(recipe.getUser().getId());
		return recipeDto;
	}

	public Recipe toEntity(RecipeDto recipeDto) {
		if(recipeDto==null) {
			throw new IllegalArgumentException("Recipe parmeter is null");
		}
		Recipe recipe = new Recipe();
		recipe.setId(recipeDto.getId());
		recipe.setName(recipeDto.getName());
		recipe.setCategory(recipeDto.getCategory());
		recipe.setCookingTimeInMinutes(recipeDto.getCookingTimeInMinutes());
		recipe.setDescription(recipeDto.getDescription());
		User user=new User();
		user.setId(recipeDto.getUserId());
		recipe.setUser(user);
		return recipe;
	}

}
