package com.example.service;

import com.example.dto.BookDto;
import com.example.model.Book;

import java.util.List;


public interface BookService {
    List<BookDto> getAllBookList();

    Book saveBook(Book book);

    Book getBookById(Integer idBook);


    Book updateBook(Book book, Integer id);

    void deleteBookById(Integer idBook);

}
