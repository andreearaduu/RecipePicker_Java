package com.andreearadu.recipepicker.repository;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andreearadu.recipepicker.model.Category;
import com.andreearadu.recipepicker.model.Ingredient;
import com.andreearadu.recipepicker.model.Recipe;

@Repository
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	public RecipeRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Recipe> getAll(String recipeName, Category category, Integer startCookingTime, Integer endCookingTime,
			Collection<String> ingredientsName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);

		Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);
		List<Predicate> predicates = new ArrayList<>();

		if (category != null) {
			predicates.add(criteriaBuilder.equal(recipeRoot.get("category"), category));
		}

		if (recipeName != null) {
			predicates.add(criteriaBuilder.like(recipeRoot.get("name"), "%" + recipeName + "%"));
		}

		if (startCookingTime != null) {
			predicates.add(
					criteriaBuilder.greaterThanOrEqualTo(recipeRoot.get("cookingTimeInMinutes"), startCookingTime));
		}
		if (endCookingTime != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(recipeRoot.get("cookingTimeInMinutes"), endCookingTime));
		}

		if (ingredientsName != null && !ingredientsName.isEmpty()) {
			Join<Recipe, Ingredient> recipeIngredients = recipeRoot.join("ingredients", JoinType.INNER);
			predicates.add(recipeIngredients.get("name").in(ingredientsName));
		}
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Recipe> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
