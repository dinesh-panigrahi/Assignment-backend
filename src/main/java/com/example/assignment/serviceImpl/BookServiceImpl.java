package com.example.assignment.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.dto.BookDto;
import com.example.assignment.entity.Book;
import com.example.assignment.repo.BookRepo;
import com.example.assignment.repo.UserRepo;
import com.example.assignment.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepo bookRepo;

	@Autowired
	UserRepo userRepo;

	@Override
	public BookDto saveBook(BookDto bookDto) {
		Book book = new Book(bookDto.getId(), bookDto.getBookName(), userRepo.findById(bookDto.getUserId()).get());
		Book updatedBook = bookRepo.save(book);

		BookDto updatedBookDto = new BookDto(updatedBook.getId(), updatedBook.getBookName(),
				updatedBook.getUser().getId());
		return updatedBookDto;

	}

	@Override
	public List<BookDto> getAllBooks() {

		List<Book> books = bookRepo.findAll();

		List<BookDto> bookDtos = books.stream()
				.map(book -> new BookDto(book.getId(), book.getBookName(), book.getUser().getId()))
				.collect(Collectors.toList());

		return bookDtos;

	}

	@Override
	public void deleteBook(long bookId) {
		bookRepo.deleteById(bookId);

	}

}
