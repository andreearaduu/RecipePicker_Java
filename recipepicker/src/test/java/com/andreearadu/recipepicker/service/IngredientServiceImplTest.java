package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.model.Ingredient;

import com.andreearadu.recipepicker.repository.IngredientRepository;

@SpringBootTest
public class IngredientServiceImplTest {

	private IngredientMapper mapper;
	
	private IngredientServiceImpl service;
	
	@MockBean
	private IngredientRepository repository;

	@Before
	public void setUp() {

		repository = mock(IngredientRepository.class);
		mapper = new IngredientMapper();
		service = new IngredientServiceImpl(repository, mapper);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllIngredientsTest() {

		Collection<Ingredient> expectedIngredientsSet = new HashSet<Ingredient>();

		Ingredient ingredientOne = initIngredient(1L, "flour");
		Ingredient ingredientTwo = initIngredient(2L, "sugar");
		Ingredient ingredientThree = initIngredient(3L, "apple");
		expectedIngredientsSet.add(ingredientOne);
		expectedIngredientsSet.add(ingredientTwo);
		expectedIngredientsSet.add(ingredientThree);

		when(repository.findAll()).thenReturn(expectedIngredientsSet);

		assertEquals(3, service.getAllIngredients().size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getIngredientByNullNameLikeTest() {

		when(repository.findIngredientByNameLike(null)).thenThrow(CustomIllegalParameterException.class);
		service.getIngredients(null);

	}

	@Test
	public void getIngredientByNameLikeTest() {

		Collection<Ingredient> setIngredients = new HashSet<Ingredient>();
		setIngredients.add(initIngredient(1L, "flour"));
		setIngredients.add(initIngredient(2L, "black flour"));

		when(repository.findIngredientByNameLike("flour")).thenReturn(setIngredients);

		assertEquals(2, service.getIngredients("flour").size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullIngredientTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addIngredient(null);
	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addExistingIngredientTest() {

		Ingredient ingredient = initIngredient(1L, "flour");
		repository.save(ingredient);

		when(repository.save(ingredient)).thenThrow(CustomIllegalParameterException.class);
		service.addIngredient(mapper.toDto(ingredient));
	}

	@Test
	public void addIngredientTest() {

		Ingredient ingredient = initIngredient(1L, "flour");

		when(repository.save(Mockito.any(Ingredient.class))).thenReturn(ingredient);

		assertEquals("flour", service.addIngredient(mapper.toDto(ingredient)).getName());
		assertEquals(1L, service.addIngredient(mapper.toDto(ingredient)).getId());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void removeNullIngredientTest() {

		doThrow(CustomIllegalParameterException.class).when(repository).delete(null);
		service.removeIngredient(null);
	}

	@Test
	public void removeIngredientTest() {

		Ingredient ingredient = initIngredient(1L, "flour");

		service.removeIngredient(mapper.toDto(ingredient));

		verify(repository, times(1)).delete(ArgumentMatchers.any());

	}

	private Ingredient initIngredient(long id, String name) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(id);
		ingredient.setName(name);
		return ingredient;
	}
}
