package com.example.service.impl;

import com.example.dto.BookDto;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<BookDto> getAllBookList() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : bookList) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setName(book.getName());
            bookDto.setDescription(book.getDescription());
            bookDto.setPrice(book.getPrice());
            if (book.getAuthor_id() != null) {
                Author author = authorRepository.findById(book.getAuthor_id()).orElse(null);
                bookDto.setAuthor(author.getName());
            }
            if (book.getImage() == null || book.getImage().equals("")) {
                bookDto.setImage("no-image.png");
            } else {
                bookDto.setImage(book.getImage());
            }

            bookDtos.add(bookDto);
        }

        return bookDtos;
    }


    @Override
    public Book saveBook(Book book) {
        // TODO Auto-generated method stub
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Integer idBook) {
        // TODO Auto-generated method stub
        return bookRepository.findById(idBook).orElse(null);
    }

    @Override
    public Book updateBook(Book book, Integer id) {
        // get from database by id
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setName(book.getName());
            existingBook.setPrice(book.getPrice());
            existingBook.setDescription(book.getDescription());
            existingBook.setAuthor_id(book.getAuthor_id());
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBookById(Integer idBook) {
        // TODO Auto-generated method stub
        bookRepository.deleteById(idBook);
    }

}
