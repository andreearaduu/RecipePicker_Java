package com.andreearadu.recipepicker.mapper;

import org.springframework.stereotype.Component;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.model.User;

@Component
public class UserMapper {

	public UserDto toDto(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User paramater is null");
		}
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		return userDto;
	}

	public User toEntity(UserDto userDto) {
		if (userDto == null) {
			throw new IllegalArgumentException("User paramater is null");
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		return user;
	}
}
