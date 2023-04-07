package com.example.bookworld.service;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.CreateBookModel;
import com.example.bookworld.Models.DTO.EditBookModel;
import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.Models.Entities.Book;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ConditionRepository mockConditionRepository;
    @Mock
    private GenreRepository mockGenreRepository;
    @Mock
    private AuthorRepository mockAuthorRepository;
    @Mock
    private BookRepository mockBookRepository;
    @Mock
    private ModelMapper mockModelMapper;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService =
                new BookService(mockUserRepository, mockConditionRepository, mockGenreRepository, mockAuthorRepository, mockBookRepository, mockModelMapper);
    }

    @Test
    void createBookOfferCorrectly() {
        CreateBookModel createBookModel = new CreateBookModel();

        String username = "iskrata1";

        User seller = new User();
        seller.setUsername(username);

        when(mockUserRepository.findByUsername(username)).thenReturn(seller);
        when(mockModelMapper.map(createBookModel, Book.class)).thenReturn(new Book());

        bookService.createBookOffer(createBookModel, username);
        verify(mockBookRepository).saveAndFlush(any());
    }

    @Test
    void getAllBooksCorrectly() {
        List<Book> customBooks = new ArrayList<>();
        customBooks.add(new Book());
        customBooks.add(new Book());

        BookModel[] bookModels = new BookModel[2];

        when(mockBookRepository.findAll()).thenReturn(customBooks);
        when(mockModelMapper.map(customBooks, BookModel[].class)).thenReturn(bookModels);

        List<BookModel> allBooks = bookService.getAllBooks();

        Assertions.assertEquals(customBooks.size(), allBooks.size());
    }

    @Test
    void findBookByIdCorrectly() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);

        BookModel bookModel = new BookModel();
        bookModel.setId(id);

        bookModel.setAuthor(new Author());

        when(mockBookRepository.findBookById(id)).thenReturn(book);
        when(mockModelMapper.map(book, BookModel.class)).thenReturn(bookModel);

        BookModel bookById = bookService.findBookById(id);

        Assertions.assertEquals(id, bookById.getId());
    }

    @Test
    void deleteBookByIdCorrectly() {
        Long id = 1L;

        bookService.deleteBook(id);

        verify(mockBookRepository).deleteById(id);
    }

    @Test
    void addCountryAndBirthDateToAuthorCorrectly() {
        Long id = 1L;
        String country = "France";
        String birthDate = "1987-12-16";

        Author author = new Author();
        author.setCountry(country);
        author.setBirthDate(birthDate);
        author.setName("Florian");

        Book book = new Book();
        book.setAuthor(author);
        BookModel bookModel = new BookModel();
        bookModel.setAuthor(author);

        EditBookModel editBookModel = new EditBookModel(country, birthDate);

        when(mockBookRepository.findBookById(id)).thenReturn(book);
        when(mockModelMapper.map(book, BookModel.class)).thenReturn(bookModel);
        when(mockAuthorRepository.getAuthorByName(author.getName())).thenReturn(author);

        BookModel returnedBook = bookService.addCountryAndBirthDateToAuthor(id, editBookModel);

        verify(mockAuthorRepository).saveAndFlush(author);
        Assertions.assertEquals(country, returnedBook.getAuthor().getCountry());
        Assertions.assertEquals(birthDate, returnedBook.getAuthor().getBirthDate());

    }

    @Test
    void buyBookCorrectly() {
        Long id = 1L;
        String username = "iskrata1";

        User buyer = new User();
        Book book = new Book();

        when(mockUserRepository.findByUsername(username)).thenReturn(buyer);
        when(mockBookRepository.findBookById(id)).thenReturn(book);

        bookService.buyBook(id, username);
        verify(mockBookRepository).saveAndFlush(any());
    }

    @Test
    void findAllBooksWithoutBuyerCorrectly() {
        Long sellerId = 1L;
        Long otherId = 2L;
        String username = "iskrata1";

        User seller = new User();
        seller.setId(sellerId);
        User other = new User();
        other.setId(otherId);

        Book book1 = new Book();
        book1.setSeller(seller);

        Book book2 = new Book();
        book2.setSeller(other);

        List<Book> customBooks = new ArrayList<>();
        customBooks.add(book1);
        customBooks.add(book2);

        BookModel[] bookModels = new BookModel[1];

        when(mockUserRepository.findByUsername(username)).thenReturn(seller);
        when(mockBookRepository.findBooksBySellerIdNot(sellerId)).thenReturn(List.of(book2));

        when(mockModelMapper.map(List.of(book2), BookModel[].class)).thenReturn(bookModels);

        List<BookModel> allBooksWithoutBuyer = bookService.getAllBooksWithoutBuyer(username);

        Assertions.assertEquals(bookModels.length, allBooksWithoutBuyer.size());
    }

    @Test
    void findMyBooksCorrectly() {
        Long sellerId = 1L;
        Long otherId = 2L;
        String username = "iskrata1";

        User seller = new User();
        seller.setId(sellerId);
        User other = new User();
        other.setId(otherId);

        Book book1 = new Book();
        book1.setSeller(seller);

        Book book2 = new Book();
        book2.setSeller(other);

        List<Book> customBooks = new ArrayList<>();
        customBooks.add(book1);
        customBooks.add(book2);

        BookModel[] bookModels = new BookModel[1];

        when(mockUserRepository.findByUsername(username)).thenReturn(seller);
        when(mockBookRepository.findBooksBySellerId(sellerId)).thenReturn(List.of(book2));

        when(mockModelMapper.map(List.of(book2), BookModel[].class)).thenReturn(bookModels);

        List<BookModel> myBooks = bookService.findMyBooks(username);

        Assertions.assertEquals(bookModels.length, myBooks.size());
    }

    @Test
    void getBookByIdOptionalCorrectly() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);

        when(mockBookRepository.getBookById(id)).thenReturn(Optional.of(book));

        Optional<Book> bookById = bookService.getBookById(id);

        Assertions.assertNotNull(bookById);
    }

    @Test
    void getAllBooksForScheduling() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());

        when(mockBookRepository.findAll()).thenReturn(books);

        List<Book> allBooksForScheduling = bookService.getAllBooksForScheduling();

        Assertions.assertEquals(books.size(), allBooksForScheduling.size());
    }
}
