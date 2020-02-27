package com.andreearadu.recipepicker.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;
import com.andreearadu.recipepicker.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository repository;
	private final IngredientMapper ingredientMapper;

	@Autowired
	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {

		this.repository = ingredientRepository;
		this.ingredientMapper = ingredientMapper;
	}

	@Override
	public Collection<IngredientDto> getAllIngredients() {
		return repository.findAll().stream().map(ingredientMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Collection<IngredientDto> getIngredients(String name) {
		if (name == null) {
			throw new CustomIllegalParameterException("Name parameter is null");
		}
		return repository.findIngredientByNameLike(name).stream().map(ingredientMapper::toDto)
				.collect(Collectors.toSet());

	}

	@Override
	public IngredientDto addIngredient(IngredientDto ingredientDto) {
		if (ingredientDto == null) {
			throw new CustomIllegalParameterException("Ingredient parameter is null");
		}

		if (repository.existsById(ingredientDto.getId()) == true) {
			throw new CustomIllegalParameterException("Ingredient already exists");
		}
		return ingredientMapper.toDto(repository.save(ingredientMapper.toEntity(ingredientDto)));
	}

	@Override
	public void removeIngredient(IngredientDto ingredientDto) {

		if (ingredientDto == null) {
			throw new CustomIllegalParameterException("Ingredient parameter is null");
		}
		repository.delete(ingredientMapper.toEntity(ingredientDto));

	}

}
