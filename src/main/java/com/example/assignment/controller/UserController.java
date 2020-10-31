package com.example.assignment.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dto.BookDto;
import com.example.assignment.dto.UserDto;
import com.example.assignment.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@PutMapping("/updateUser")
	public Callable<ResponseEntity<UserDto>> updateUser(@RequestBody UserDto userDto) {

		return () -> {
			UserDto dto = userService.updateUser(userDto);
			return ResponseEntity.ok(dto);
		};
	}

	@GetMapping
	public Callable<ResponseEntity<List<UserDto>>> getAllUsers() {

		return () -> {

			List<UserDto> dtos = userService.getAllUsers();
			return ResponseEntity.ok(dtos);
		};
	}

	@GetMapping("/{userId}")
	public Callable<ResponseEntity<UserDto>> getUserId(@PathVariable("userId") long userId) {

		return () -> {

			UserDto dto = userService.getUserId(userId);
			return ResponseEntity.ok(dto);
		};
	}

	@GetMapping("getBooks/{userId}")
	public Callable<ResponseEntity<List<BookDto>>> getBooksByUserId(@PathVariable("userId") long userId) {

		return () -> {

			List<BookDto> res = userService.getBooksByUserId(userId);
			return ResponseEntity.ok(res);
		};
	}

	@DeleteMapping("/{userId}")
	public Callable<ResponseEntity<Void>> deleteEmployee(@PathVariable("userId") long userId) {

		return () -> {

			userService.deleteUser(userId);
			return ResponseEntity.noContent().build();
		};
	}

}
