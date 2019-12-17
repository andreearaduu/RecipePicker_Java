package com.andreearadu.recipepicker.mapper;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.model.User;

public class UserMapper {

	public UserDto toDto(User user) {
		if(user==null) {
			throw new CustomIllegalParameterException("User paramater is null");
		}

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	public User toEntity(UserDto userDto) {
		if(userDto==null) {
			throw new CustomIllegalParameterException("User paramater is null");
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		return user;
	}
}
