package com.example.bookstore.service;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BooksService {

    void save(Book book);

    List<Book> findAll();
}
