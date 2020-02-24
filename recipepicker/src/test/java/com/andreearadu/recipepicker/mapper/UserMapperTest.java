package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;

import com.andreearadu.recipepicker.model.User;

public class UserMapperTest {

	User user;
	UserDto userDto;
	UserMapper userMapper;

	@Before
	public void setUp() {
		this.userMapper = new UserMapper();
	}

	@Test
	public void testMappToDto() {
		initUser();
		UserDto userDto = userMapper.toDto(user);

		assertThat(userDto.getId()).isEqualTo(user.getId());
		assertThat(userDto.getEmail()).isEqualTo(user.getEmail());

	}

	@Test
	public void testMappToEntity() {

		initUserDto();
		User user = userMapper.toEntity(userDto);

		assertThat(user.getId()).isEqualTo(userDto.getId());
		assertThat(user.getEmail()).isEqualTo(userDto.getEmail());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullUserToDto() {
		userMapper = new UserMapper();
		userDto = userMapper.toDto(user);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void testNullUserToEntity() {
		userMapper = new UserMapper();
		user = userMapper.toEntity(userDto);

	}

	private void initUser() {
		user = new User();
		user.setId(1L);
		user.setEmail("andreea.radu@yahoo.com");

	}

	private void initUserDto() {
		userDto = new UserDto();
		userDto.setId(1L);
		userDto.setEmail("andreea.radu@gmail.com");

	}
}
