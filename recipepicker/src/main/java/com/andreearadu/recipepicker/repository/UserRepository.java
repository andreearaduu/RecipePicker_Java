package com.andreearadu.recipepicker.repository;

import java.util.Collection;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Collection<User> findAll();

<<<<<<< HEAD
=======
	Collection<User> findAll();

>>>>>>> 9fae0be1821fe78c21230e7c5ef17247b46c6f04
}
