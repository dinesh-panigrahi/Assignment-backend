package com.example.assignment.service;

import java.util.List;

import com.example.assignment.dto.BookDto;
import com.example.assignment.dto.UserDto;

public interface UserService {

	UserDto saveUser(UserDto userDto);

	UserDto getUserId(long userId);

	List<BookDto> getBooksByUserId(long userId);

	List<UserDto> getAllUsers();

	UserDto updateUser(UserDto userDto);

	public void deleteUser(long userId);

}
