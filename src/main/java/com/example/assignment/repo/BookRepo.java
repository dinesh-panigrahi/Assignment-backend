package com.example.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assignment.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}
