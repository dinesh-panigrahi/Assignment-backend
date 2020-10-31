package com.example.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.assignment.dto.BookDto;
import com.example.assignment.dto.UserDto;
import com.example.assignment.entity.Book;
import com.example.assignment.entity.User;
import com.example.assignment.repo.UserRepo;
import com.example.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(),
				bcryptEncoder.encode(userDto.getPassword()));
		User updatedUser = userRepo.save(user);

		UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(),
				updatedUser.getPassword());
		return updatedUserDto;

	}

	@Override
	public UserDto getUserId(long userId) {
		User user = userRepo.findById(userId).get();
		User updatedUser = userRepo.save(user);

		UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(),
				updatedUser.getPassword());
		return updatedUserDto;

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();

		List<UserDto> userDtos = users.stream()
				.map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword()))
				.collect(Collectors.toList());

		return userDtos;

	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword());
		User updatedUser = userRepo.save(user);

		UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(),
				updatedUser.getPassword());
		return updatedUserDto;

	}

	@Override
	public void deleteUser(long userId) {
		userRepo.deleteById(userId);

	}

	@Override
	public List<BookDto> getBooksByUserId(long userId) {
		List<Book> books = userRepo.findById(userId).get().getBooks();
		List<BookDto> bookDtos = books.stream()
				.map(book -> new BookDto(book.getId(), book.getBookName(), book.getUser().getId()))
				.collect(Collectors.toList());
		return bookDtos;
	}

}
