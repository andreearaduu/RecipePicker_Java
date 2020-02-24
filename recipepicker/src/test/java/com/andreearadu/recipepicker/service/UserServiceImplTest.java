package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;

import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.andreearadu.recipepicker.dto.UserDto;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.UserMapper;
import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	private UserMapper mapper;

	@MockBean
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl service;

	@Before
	public void setUp() {

		repository = mock(UserRepository.class);
		mapper = new UserMapper();
		service = new UserServiceImpl(repository, mapper);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllUsersTest() {

		Collection<UserDto> setUsersDto = new HashSet<UserDto>();
		UserDto userOne = initUserDto(1L, "aaa@yahoo.com");
		UserDto userTwo = initUserDto(2L, "bbb@yahoo.com");
		UserDto userThree = initUserDto(3L, "ccc@yahoo.com");
		setUsersDto.add(userOne);
		setUsersDto.add(userTwo);
		setUsersDto.add(userThree);

		when(repository.findAll()).thenReturn(setUsersDto.stream().map(mapper::toEntity).collect(Collectors.toSet()));

		Collection<UserDto> expectedUsersSet = service.getAllUsers();

		assertEquals(3, expectedUsersSet.size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getUserByNullEmail() {

		when(repository.findByEmail(null)).thenThrow(CustomIllegalParameterException.class);

		service.getUserByEmail(null);

	}

	@Test
	public void getUserByEmailTest() {

		String email = "aaa@yahoo.com";
		UserDto user = initUserDto(1L, email);

		when(repository.findByEmail(email)).thenReturn(mapper.toEntity(user));

		UserDto expectedUser = service.getUserByEmail(email);

		assertEquals(1L, expectedUser.getId());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullUserTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addUser(null);

	}

	@Test
	public void addUserTest() {

		UserDto userDto = initUserDto(1L, "bbb@yahoo.com");

		when(repository.save(any(User.class))).thenReturn(mapper.toEntity(userDto));

		UserDto userAdded = service.addUser(userDto);

		assertEquals("bbb@yahoo.com", userAdded.getEmail());
		assertEquals(1L, userAdded.getId());

	}

	private UserDto initUserDto(Long id, String email) {

		UserDto userDto = new UserDto();
		userDto.setEmail(email);
		userDto.setId(id);
		return userDto;
	}

}
