package com.andreearadu.recipepicker.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Collection<User> findAll();

	User findByName(String name);

	User findByEmail(String email);
}
