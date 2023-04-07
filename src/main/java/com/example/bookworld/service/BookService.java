package com.example.bookworld.service;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.CreateBookModel;
import com.example.bookworld.Models.DTO.EditBookModel;
import com.example.bookworld.Models.Entities.*;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public BookModel findBookById(Long id) {
        Book book = this.bookRepository.findBookById(id);

        BookModel bookToShow = modelMapper.map(book, BookModel.class);

        if(bookToShow.getAuthor().getCountry() == null) {
            bookToShow.getAuthor().setCountry("The Admin is in process of adding author's country. :)");
        }

        if(bookToShow.getAuthor().getBirthDate() == null) {
            bookToShow.getAuthor().setBirthDate("The Admin is in process of adding author's birth date. :)");
        }

        return bookToShow;
    }

    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    public BookModel addCountryAndBirthDateToAuthor(Long id, EditBookModel editBookModel) {
        Book book = this.bookRepository.findBookById(id);

        String authorName = book.getAuthor().getName();
        Author author = this.authorRepository.getAuthorByName(authorName);

        author.setCountry(editBookModel.getCountry());
        author.setBirthDate(editBookModel.getBirthDate());

        this.authorRepository.saveAndFlush(author);

        book.getAuthor().setCountry(editBookModel.getCountry());
        book.getAuthor().setBirthDate(editBookModel.getBirthDate());

        BookModel bookToShow = modelMapper.map(book, BookModel.class);

//        bookToShow.getAuthor().setCountry(editBookModel.getCountry());
//        book.getAuthor().setBirthDate(editBookModel.getBirthDate());

        return bookToShow;
    }


    public void buyBook(Long id, String name) {
        User buyer = this.userRepository.findByUsername(name);
        Book book = this.bookRepository.findBookById(id);

        book.setBuyer(buyer);
        book.setSeller(null);
        this.bookRepository.saveAndFlush(book);
    }


    public List<BookModel> getAllBooksWithoutBuyer(String name) {
        User user = this.userRepository.findByUsername(name);
        List<Book> booksBySellerIdNot = this.bookRepository.findBooksBySellerIdNot(user.getId());

        BookModel[] mappedOffers = this.modelMapper.map(booksBySellerIdNot, BookModel[].class);

        List<BookModel> otherBooks = Arrays.stream(mappedOffers).toList();

        return otherBooks;
    }

    public List<BookModel> findMyBooks(String name) {
        User user = this.userRepository.findByUsername(name);
        List<Book> booksBySellerId = this.bookRepository.findBooksBySellerId(user.getId());

        BookModel[] mappedBooks = this.modelMapper.map(booksBySellerId, BookModel[].class);

        List<BookModel> myBooks = Arrays.stream(mappedBooks).toList();

        return myBooks;
    }

    public Optional<Book> getBookById(Long id) {
        Optional<Book> bookById = this.bookRepository.getBookById(id);

        return bookById;
    }

    public List<Book> getAllBooksForScheduling() {
        List<Book> allBooks = this.bookRepository.findAll();

        return allBooks;
    }
}
