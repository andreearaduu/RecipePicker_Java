package com.andreearadu.recipepicker.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Recipe;
import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.modelLayer.Stars;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	 Optional<Review> findByRecipe(Recipe recipe);
	 Optional<Review> findByStars(Stars stars);
	 Set<Review> findAll();
	

}
