package com.example.service.impl;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Author> getAll() {

        return authorRepository.findAll();
    }

    @Override
    public Author add(Author dto) {
        return authorRepository.save(dto);
    }

    @Override
    public Boolean delete(Integer id) {
        List<Book> bookList = bookRepository.findAllByAuthor_id(id);
        if (bookList.size() > 0) {
            return false;
        } else {
            authorRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author update(Author dto) {
        return authorRepository.save(dto);
    }
}
