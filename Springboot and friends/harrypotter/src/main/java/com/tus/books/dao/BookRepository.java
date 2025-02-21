package com.tus.books.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tus.books.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {


}
