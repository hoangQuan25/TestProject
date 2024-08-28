package com.example.bookstore.service.impl;

import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.CategoriesRepository;
import com.example.bookstore.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoriesRepository.findById(id).orElse(null);
    }
}
