package com.andreearadu.recipepicker.repository;

import org.springframework.data.repository.CrudRepository;

import com.andreearadu.recipepicker.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
