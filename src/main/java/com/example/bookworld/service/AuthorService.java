package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void saveAuthor(String author) {
        Author authorToSave = new Author(author, null, null, new ArrayList<>());

        Author authorFromDb = authorRepository.getAuthorByName(author);

        if(authorFromDb == null) {
            this.authorRepository.saveAndFlush(authorToSave);
        }
    }

    public Author getAuthorByName(String name) {
        Author author = this.authorRepository.getAuthorByName(name);

        return author;
    }
}
