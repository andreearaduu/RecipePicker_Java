package com.andreearadu.recipepicker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	public List<Review> findByRecipe(Recipe recipe);

}
