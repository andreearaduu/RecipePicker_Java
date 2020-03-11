package com.andreearadu.recipepicker.service;

import java.util.Collection;
import com.andreearadu.recipepicker.dto.UserDto;

public interface UserService {

	Collection<UserDto> getAll();
	
	UserDto getUserById(long id);

}
