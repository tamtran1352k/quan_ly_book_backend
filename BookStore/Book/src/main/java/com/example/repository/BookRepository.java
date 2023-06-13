package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT count(b) from Book b")
    public int countBook();

    @Query("SELECT b from Book b where b.author_id = :id")
    public List<Book> findAllByAuthor_id(Integer id);

    @Query("SELECT b from Book b where b.category_id = :id")
    public List<Book> findAllByCategory_id(Integer id);

}
