package com.andreearadu.recipepicker.repository;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
