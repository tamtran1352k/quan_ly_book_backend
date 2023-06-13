package com.example.service;

import com.example.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author add(Author dto);

    Boolean delete(Integer id);

    Author findById(Integer id);

    Author update(Author dto);
}