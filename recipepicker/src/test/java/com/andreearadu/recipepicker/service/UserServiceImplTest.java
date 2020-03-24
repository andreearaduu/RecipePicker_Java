package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.exceptions.UserNotFoundException;
import com.andreearadu.recipepicker.mapper.RecipeMapper;
import com.andreearadu.recipepicker.mapper.UserMapper;
import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.RecipeRepository;
import com.andreearadu.recipepicker.repository.UserRepository;

public class UserServiceImplTest {

	private UserMapper userMapper;
	private RecipeMapper recipeMapper;
	private UserRepository repository;
	private RecipeRepository recipeRepository;

	private UserServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(UserRepository.class);
		recipeRepository=mock(RecipeRepository.class);
		userMapper = new UserMapper();
		recipeMapper=new RecipeMapper();
		service = new UserServiceImpl(repository, userMapper,recipeRepository,recipeMapper);

		Collection<User> users=new HashSet<>();
		User user1=initUser(1,"email" , "name");
		User user2=initUser(2,"email" , "name");
		User user3=initUser(3,"email" , "name");
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		Collection<Recipe> recipes = new HashSet<>();
		Recipe recipeOne = initRecipe("cheese cake", Category.CAKES, 50);
		Recipe recipeTwo = initRecipe("cake", Category.CAKES, 20);
		Recipe recipeThree = initRecipe("coffe cake", Category.CAKES, 40);
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);
		
		user1.addOwnRecipes(recipes);
		user1.addToFavoriteRecipes(recipes);
		user1.addToCookedRecipes(recipes);
		
		when(repository.findAll()).thenReturn(users);
		when(repository.findById(1L)).thenReturn(Optional.of(user1));
		when(repository.findById(9L)).thenThrow(UserNotFoundException.class);
		
	}

	@Test
	public void getAllUsersTest() {
		assertEquals(3, service.getAll().size());
	}
	
	@Test
	public void getUserByIdTest() {
		assertEquals(service.getUserById(1).getId(), 1);
		assertEquals(service.getUserById(1).getName(), "name");
		assertEquals(service.getUserById(1).getEmail(), "email");
	}
	
	@Test(expected = UserNotFoundException.class)
	public void getUserByIdNotFoundTest() {
		service.getUserById(9);
	}
	@Test
	public void getRecipesOwnByUserTest() {
		assertEquals(3, service.getRecipesOwnedByUser(1).size());
	}
	@Test(expected = UserNotFoundException.class)
	public void getRecipesOwnByUserNotFoundTest() {
		service.getCookedRecipesByUser(9);
	}
	@Test
	public void getFavoriteRecipesByUserTest() {
		assertEquals(3, service.getRecipesOwnedByUser(1).size());
	}
	@Test(expected = UserNotFoundException.class)
	public void getFavoriteRecipesByUserNotFoundTest() {
		service.getCookedRecipesByUser(9);
	}
	@Test
	public void getCookedRecipesByUserTest() {
		assertEquals(3, service.getRecipesOwnedByUser(1).size());
	}
	@Test(expected = UserNotFoundException.class)
	public void getCookedRecipesByUserNotFoundTest() {
		service.getCookedRecipesByUser(9);
	}
	
	@Test
	public void addOwnRecipeToUserTest() {
		
		Recipe recipe=initRecipe("name", Category.APPETIZER, 90);
		
		when(recipeRepository.save(any())).thenReturn(recipe);
		
		assertEquals(recipe.getId(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getId());
		assertEquals(recipe.getCategory(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getCategory());
		assertEquals(recipe.getCookingTimeInMinutes(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getCookingTimeInMinutes());
		assertEquals(recipe.getDescription(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getDescription());
		assertEquals(recipe.getName(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getName());
		assertEquals(recipe.getUser().getId(), service.addOwnRecipe(recipeMapper.toDto(recipe), 1).getUserId());

	}
	@Test
	public void addFavoriteRecipeToUserTest() {
		
		Recipe recipe=initRecipe("name", Category.APPETIZER, 90);
		
		when(recipeRepository.save(any())).thenReturn(recipe);
		
		assertEquals(recipe.getId(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getId());
		assertEquals(recipe.getCategory(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getCategory());
		assertEquals(recipe.getCookingTimeInMinutes(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getCookingTimeInMinutes());
		assertEquals(recipe.getDescription(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getDescription());
		assertEquals(recipe.getName(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getName());
		assertEquals(recipe.getUser().getId(), service.addFavoriteRecipe(recipeMapper.toDto(recipe), 1).getUserId());

	}
	@Test
	public void addCookedRecipeToUserTest() {
		
		Recipe recipe=initRecipe("name", Category.APPETIZER, 90);
		
		when(recipeRepository.save(any())).thenReturn(recipe);
		
		assertEquals(recipe.getId(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getId());
		assertEquals(recipe.getCategory(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getCategory());
		assertEquals(recipe.getCookingTimeInMinutes(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getCookingTimeInMinutes());
		assertEquals(recipe.getDescription(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getDescription());
		assertEquals(recipe.getName(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getName());
		assertEquals(recipe.getUser().getId(), service.addCookedRecipe(recipeMapper.toDto(recipe), 1).getUserId());

	}
	private User initUser(long id,String email,String name) {
		User user=new User();
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		return user;
	}
	
	private Recipe initRecipe(String name, Category category, int cookingTime) {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setCategory(category);
		recipe.setCookingTimeInMinutes(cookingTime);
		User user=new User();
		user.setId(1L);
		recipe.setUser(user);
		return recipe;
	}
}
