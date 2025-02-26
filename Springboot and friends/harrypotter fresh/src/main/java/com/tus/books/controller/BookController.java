package com.tus.books.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.books.dao.BookRepository;
import com.tus.books.model.Book;


@RestController
@RequestMapping("/books")
public class BookController {
	
	private BookRepository bookrepo;
	
	@Autowired
	public BookController(BookRepository bookrepo) {
		this.bookrepo = bookrepo;
	}


	@GetMapping
	List<Book> getAllBooks() {
		return (List<Book>) bookrepo.findAll();
		
	}

	@GetMapping("/{id}")
	Optional<Book> getBookById(@PathVariable int id) {
		return bookrepo.findById(id);
	}

	
}
