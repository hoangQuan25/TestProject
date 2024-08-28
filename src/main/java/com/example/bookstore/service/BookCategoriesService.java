package com.example.bookstore.service;

import com.example.bookstore.entity.BookCategory;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.BookCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookCategoriesService {

    void save(BookCategory bookCategory);
}
