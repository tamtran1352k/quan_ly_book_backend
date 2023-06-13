package com.example.service.impl;

import com.example.model.Book;
import com.example.model.Category;
import com.example.repository.BookRepository;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Category> getAll() {

        return categoryRepository.findAll();
    }

    @Override
    public Category add(Category dto) {
        return categoryRepository.save(dto);
    }

    @Override
    public Boolean delete(Integer id) {
        List<Book> bookList = bookRepository.findAllByCategory_id(id);
        if (bookList.size() > 0) {
            return false;
        } else {
            categoryRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Integer id, Category category) {

        // get from database by id
        Category existing = categoryRepository.findById(id).orElse(null);
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return categoryRepository.save(existing);
    }
}
