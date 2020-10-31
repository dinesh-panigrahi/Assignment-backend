package com.example.assignment.service;

import java.util.List;

import com.example.assignment.dto.BookDto;

public interface BookService {

	BookDto saveBook(BookDto bookDto);

	List<BookDto> getAllBooks();

	public void deleteBook(long bookId);
}
