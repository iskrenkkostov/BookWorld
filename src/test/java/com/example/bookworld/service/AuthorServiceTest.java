package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository mockAuthorRepository;

    private AuthorService authorService;

    @BeforeEach()
     void setUp() {
        authorService = new AuthorService(mockAuthorRepository);
    }

    @Test
    void saveAuthorCorrectly() {
        String authorName = "non-existent";

        when(mockAuthorRepository.getAuthorByName(authorName)).thenReturn(null);

        authorService.saveAuthor(authorName);

        verify(mockAuthorRepository).saveAndFlush(any());

    }

    @Test
    void getAuthorByNameCorrectly() {
        String authorName = "Emily Bront";

        Author author = new Author();
        author.setName(authorName);

        when(mockAuthorRepository.getAuthorByName(authorName)).thenReturn(author);

        Author authorByName = authorService.getAuthorByName(authorName);

        Assertions.assertEquals(author.getName(), authorByName.getName());
    }


}
