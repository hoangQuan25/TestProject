package com.example.bookstore.service;

import com.example.bookstore.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService {

    List<Category> findAll();

    Category findById(Integer id);
}
