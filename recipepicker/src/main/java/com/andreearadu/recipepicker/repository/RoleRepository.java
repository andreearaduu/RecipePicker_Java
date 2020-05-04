package com.andreearadu.recipepicker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andreearadu.recipepicker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}