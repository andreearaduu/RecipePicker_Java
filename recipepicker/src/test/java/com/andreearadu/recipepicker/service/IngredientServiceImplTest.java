package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.dto.IngredientDto;

import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.model.Ingredient;

import com.andreearadu.recipepicker.repository.IngredientRepository;


@SpringBootTest
public class IngredientServiceImplTest {

	private IngredientMapper mapper;

	@MockBean
	private IngredientRepository repository;

	@InjectMocks
	private IngredientServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(IngredientRepository.class);
		mapper = new IngredientMapper();
		service = new IngredientServiceImpl(repository, mapper);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllIngredientsTest() {

		Collection<IngredientDto> expectedIngredientsSet = new HashSet<IngredientDto>();

		IngredientDto ingredientOne = initIngredientDto(1L, "flour");
		IngredientDto ingredientTwo = initIngredientDto(2L, "sugar");
		IngredientDto ingredientThree = initIngredientDto(3L, "apple");
		expectedIngredientsSet.add(ingredientOne);
		expectedIngredientsSet.add(ingredientTwo);
		expectedIngredientsSet.add(ingredientThree);

		when(repository.findAll())
				.thenReturn(expectedIngredientsSet.stream().map(mapper::toEntity).collect(Collectors.toSet()));

		Collection<IngredientDto> actualIngredientsSet = service.getAllIngredients();

		assertEquals(3, actualIngredientsSet.size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getIngredientByNullNameLikeTest() {

		when(repository.findIngredientByNameLike(null)).thenThrow(CustomIllegalParameterException.class);
		service.getIngredients(null);

	}

	@Test
	public void getIngredientByNameLikeTest() {

		Collection<IngredientDto> setIngredientsDto = new HashSet<IngredientDto>();
		setIngredientsDto.add(initIngredientDto(1L, "flour"));
		setIngredientsDto.add(initIngredientDto(2L, "black flour"));

		when(repository.findIngredientByNameLike("flour"))
				.thenReturn(setIngredientsDto.stream().map(mapper::toEntity).collect(Collectors.toSet()));

		Collection<IngredientDto> ingredientsFound = service.getIngredients("flour");

		assertEquals(2, ingredientsFound.size());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullIngredientTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addIngredient(null);
	}

	@Test
	public void addIngredientTest() {

		//IngredientDto ingredientDto = initIngredientDto(1L, "flour");
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(1L);
		ingredientDto.setName("flour");
		
		when(repository.save(Mockito.any(Ingredient.class))).thenReturn(mapper.toEntity(ingredientDto));

		IngredientDto ingredientAdded = service.addIngredient(ingredientDto);

		assertEquals("flour", ingredientAdded.getName());
		assertEquals(1L, ingredientAdded.getId());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void removeNullIngredientTest() {

		doThrow(CustomIllegalParameterException.class).when(repository).delete(null);
		service.removeIngredient(null);
	}

	@Test
	public void removeIngredientTest() {

		IngredientDto ingredientDto = initIngredientDto(1L, "flour");

		service.removeIngredient(ingredientDto);

		verify(repository, times(1)).delete(ArgumentMatchers.any());

	}

	private IngredientDto initIngredientDto(long id, String name) {
		IngredientDto ingredientDto = new IngredientDto();
		ingredientDto.setId(id);
		ingredientDto.setName(name);
		return ingredientDto;
	}
}
