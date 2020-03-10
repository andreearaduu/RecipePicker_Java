package com.andreearadu.recipepicker.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.IllegalUserParameterException;
import com.andreearadu.recipepicker.model.User;

public class UserMapperTest {

	UserMapper userMapper;

	@Before
	public void setUp() {
		this.userMapper = new UserMapper();
	}

	@Test
	public void testMappToDto() {
		User user = initUser();
		UserDto userDto = userMapper.toDto(user);

		assertThat(userDto.getId()).isEqualTo(user.getId());
		assertThat(userDto.getEmail()).isEqualTo(user.getEmail());

	}

	@Test
	public void testMappToEntity() {

		UserDto userDto = initUserDto();
		User user = userMapper.toEntity(userDto);

		assertThat(user.getId()).isEqualTo(userDto.getId());
		assertThat(user.getEmail()).isEqualTo(userDto.getEmail());

	}

	@Test(expected = IllegalUserParameterException.class)
	public void testNullUserToDto() {
		User user = null;
		userMapper.toDto(user);

	}

	@Test(expected = IllegalUserParameterException.class)
	public void testNullUserToEntity() {
		UserDto userDto = null;
		userMapper.toEntity(userDto);

	}

	private User initUser() {
		User user = new User();
		user.setId(1L);
		user.setEmail("andreea.radu@yahoo.com");
		return user;

	}

	private UserDto initUserDto() {
		UserDto userDto = new UserDto();
		userDto.setId(1L);
		userDto.setEmail("andreea.radu@gmail.com");
		return userDto;
	}
}
