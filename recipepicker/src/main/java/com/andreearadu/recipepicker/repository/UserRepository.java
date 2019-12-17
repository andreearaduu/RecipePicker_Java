package com.andreearadu.recipepicker.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	Set<User> findAll();
}
