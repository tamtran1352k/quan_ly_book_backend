package com.example.service;

import com.example.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category add(Category dto);

    Boolean delete(Integer id);

    Category findById(Integer id);

    Category update(Integer id, Category dto);
}