package com.example.bookworld.service;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.CreateBookModel;
import com.example.bookworld.Models.Entities.*;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(UserRepository userRepository, ConditionRepository conditionRepository, GenreRepository genreRepository, AuthorRepository authorRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public void createBookOffer(CreateBookModel createBookModel, String name) {
        User seller = userRepository.findByUsername(name);

        ConditionType conditionType = createBookModel.getCondition();
        Condition condition = conditionRepository.getConditionByName(conditionType);

        Genre genre = genreRepository.getGenreByName(createBookModel.getGenre());

        String authorName = createBookModel.getAuthor();
        Author author = authorRepository.getAuthorByName(authorName);

        Book book = modelMapper.map(createBookModel, Book.class);

        book.setSeller(seller);
        book.setCondition(condition);
        book.setGenre(genre);
        book.setAuthor(author);


        this.bookRepository.saveAndFlush(book);
    }

    public List<BookModel> getAllBooks() {
        List<Book> allBooks = this.bookRepository.findAll();

        BookModel[] modelBooks = modelMapper.map(allBooks, BookModel[].class);

        List<BookModel> mappedBooks = Arrays.stream(modelBooks).toList();

        return mappedBooks;
    }
}
