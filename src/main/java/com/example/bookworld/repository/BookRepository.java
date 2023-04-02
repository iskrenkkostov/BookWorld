package com.example.bookworld.repository;

import com.example.bookworld.Models.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Book findBookById(Long id);

    List<Book> findBooksBySellerIdNot(Long id);

    List<Book> findBooksBySellerId(Long id);

    Optional<Book> getBookById(Long id);
}
