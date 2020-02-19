package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Review;
import com.andreearadu.recipepicker.model.Stars;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByRecipeName(String name);

	Collection<Review> findByStars(Stars stars);

	Collection<Review> findAll();

}
