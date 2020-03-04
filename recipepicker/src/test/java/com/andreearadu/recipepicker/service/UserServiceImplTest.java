package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import com.andreearadu.recipepicker.dto.RecipeDto;
import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.IllegalRecipeParameterException;
import com.andreearadu.recipepicker.exceptions.IllegalUserParameterException;
import com.andreearadu.recipepicker.mapper.UserMapper;
import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.UserRepository;

public class UserServiceImplTest {

	private UserMapper mapper;
	private UserRepository repository;

	private UserServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(UserRepository.class);
		mapper = new UserMapper();
		service = new UserServiceImpl(repository, mapper);

	}

	@Test(expected = IllegalUserParameterException.class)
	public void registerNullUserTest() {
		when(repository.save(null)).thenThrow(IllegalUserParameterException.class);
		service.register(null);

	}

	@Test
	public void registerUserTest() {
		User user = new User();
		user.setEmail("bbb@yahoo.com");

		when(repository.save(any(User.class))).thenReturn(user);
		assertEquals(user.getEmail(), service.register(mapper.toDto(user)).getEmail());
		assertEquals(user.getId(), service.register(mapper.toDto(user)).getId());

	}

	@Test
	public void addFavoriteRecipeToUserTest() {
		UserDto userDto = new UserDto();
		userDto.setEmail("aaa@yahoo.com");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");

		assertEquals(true, service.addFavoriteRecipe(recipeDto, userDto));

	}

	@Test
	public void addDuplicateFavoriteRecipeToUser() {
		UserDto userDto = new UserDto();
		userDto.setEmail("aaa@yahoo.com");
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");
		service.addFavoriteRecipe(recipeDto, userDto);

		assertEquals(false, service.addFavoriteRecipe(recipeDto, userDto));
	}

	@Test(expected = IllegalRecipeParameterException.class)
	public void addNullFavoriteRecipeToUserTest() {
		UserDto userDto = new UserDto();
		userDto.setEmail("aaa@yahoo.com");
		service.addFavoriteRecipe(null, userDto);

	}

	@Test(expected = IllegalUserParameterException.class)
	public void addFavoriteRecipeToNullUserTest() {
		RecipeDto recipeDto = new RecipeDto();
		recipeDto.setName("aaa");
		service.addFavoriteRecipe(recipeDto, null);

	}

}
