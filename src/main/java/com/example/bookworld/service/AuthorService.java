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

        if(authorRepository.getAuthorByName(author) == null) {
            this.authorRepository.saveAndFlush(authorToSave);
        }
    }
}
