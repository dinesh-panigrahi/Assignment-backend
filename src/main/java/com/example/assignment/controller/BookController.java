package com.example.assignment.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.dto.BookDto;
import com.example.assignment.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/addBook")
	public Callable<ResponseEntity<BookDto>> saveBook(@RequestBody BookDto bookDto) {

		return () -> {

			BookDto dto = bookService.saveBook(bookDto);
			return ResponseEntity.ok(dto);
		};
	}

	@PutMapping("/updateBook")
	public Callable<ResponseEntity<BookDto>> updateBook(@RequestBody BookDto bookDto) {

		return () -> {

			BookDto dto = bookService.saveBook(bookDto);
			return ResponseEntity.ok(dto);
		};
	}

	@GetMapping
	public Callable<ResponseEntity<List<BookDto>>> getAllUsers() {

		return () -> {

			List<BookDto> dtos = bookService.getAllBooks();
			return ResponseEntity.ok(dtos);
		};
	}

	@DeleteMapping("/{bookId}")
	public Callable<ResponseEntity<Void>> deleteEmployee(@PathVariable("bookId") long bookId) {

		return () -> {

			bookService.deleteBook(bookId);
			return ResponseEntity.noContent().build();
		};
	}

}
