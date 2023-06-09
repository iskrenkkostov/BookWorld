package com.example.bookworld.repository;

import com.example.bookworld.Models.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getAuthorByName(String name);
}
