package com.andreearadu.recipepicker.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreearadu.recipepicker.dto.IngredientDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.IngredientMapper;

import com.andreearadu.recipepicker.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	private IngredientRepository repository;
	private IngredientMapper ingredientMapper;

	@Autowired

	public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {

		this.repository = ingredientRepository;
		this.ingredientMapper = ingredientMapper;
	}

	@Override
	public Set<IngredientDto> getAllIngredients() {
		return repository.findAll().stream().map(ingredientMapper::toDto).collect(Collectors.toSet());
	}

	@Override
	public Set<IngredientDto> getIngredientByNameLike(String name) {
		if (name == null) {
			throw new CustomIllegalParameterException("Name parameter is null");
		}
		return repository.findIngredientByNameLike(name).stream()
				.map(ingredientMapper::toDto).collect(Collectors.toSet());
		
	}

	@Override
	public IngredientDto addIngredient(IngredientDto ingredientDto) {
		if (ingredientDto == null) {
			throw new CustomIllegalParameterException("Ingredient parameter is null");
		}

		return ingredientMapper.toDto(repository.save(ingredientMapper.toEntity(ingredientDto)));
	}

	@Override
	public boolean removeIngredient(IngredientDto ingredientDto) {

		if (ingredientDto == null) {
			throw new CustomIllegalParameterException("Ingredient parameter is null");
		}
		repository.delete(ingredientMapper.toEntity(ingredientDto));
		return true;
	}

}
