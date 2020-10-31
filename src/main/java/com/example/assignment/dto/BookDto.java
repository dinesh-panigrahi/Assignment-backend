package com.example.assignment.dto;

public class BookDto {
	
	private long id;
	private String bookName;
	private long userId;
	
	
	public BookDto() {
		super();
	}


	public BookDto(long id, String bookName, long userId) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.userId = userId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
