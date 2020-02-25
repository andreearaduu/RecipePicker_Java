package com.andreearadu.recipepicker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.andreearadu.recipepicker.exceptions.CustomIllegalParameterException;
import com.andreearadu.recipepicker.mapper.UserMapper;
import com.andreearadu.recipepicker.model.User;
import com.andreearadu.recipepicker.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	private UserMapper mapper;

	@MockBean
	private UserRepository repository;

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

		Collection<User> setUsers = new HashSet<User>();
		User userOne = initUser(1L, "aaa@yahoo.com");
		User userTwo = initUser(2L, "bbb@yahoo.com");
		User userThree = initUser(3L, "ccc@yahoo.com");
		setUsers.add(userOne);
		setUsers.add(userTwo);
		setUsers.add(userThree);

		when(repository.findAll()).thenReturn(setUsers);

		assertEquals(3, service.getAllUsers().size());
		verify(repository, times(1)).findAll();

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getUserByNullEmail() {

		when(repository.findByEmail(null)).thenThrow(CustomIllegalParameterException.class);

		service.getUserByEmail(null);

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void getUserByInexistentEmail() {

		String email = "bbb@yahoo.com";
		when(repository.findByEmail(email)).thenThrow(CustomIllegalParameterException.class);
		service.getUserByEmail(email);

	}

	@Test
	public void getUserByEmailTest() {

		String email = "aaa@yahoo.com";
		User user = initUser(1L, email);

		when(repository.findByEmail(email)).thenReturn(Optional.of(user));

		assertEquals(1L, service.getUserByEmail(email).getId());

	}

	@Test(expected = CustomIllegalParameterException.class)
	public void addNullUserTest() {

		when(repository.save(null)).thenThrow(CustomIllegalParameterException.class);
		service.addUser(null);

	}

	@Test
	public void addUserTest() {

		User user = initUser(1L, "bbb@yahoo.com");

		when(repository.save(any(User.class))).thenReturn(user);

		assertEquals("bbb@yahoo.com", service.addUser(mapper.toDto(user)).getEmail());
		assertEquals(1L, service.addUser(mapper.toDto(user)).getId());

	}

	private User initUser(Long id, String email) {
		User user = new User();
		user.setEmail(email);
		user.setId(id);
		return user;
	}

}
