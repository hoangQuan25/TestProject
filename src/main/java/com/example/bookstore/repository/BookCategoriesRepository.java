package com.example.bookstore.repository;

import com.example.bookstore.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoriesRepository extends JpaRepository<BookCategory, Integer> {
}
