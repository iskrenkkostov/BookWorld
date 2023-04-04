package com.example.bookworld.schedules;

import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.Models.Entities.Book;
import com.example.bookworld.repository.AuthorRepository;
import com.example.bookworld.service.AuthorService;
import com.example.bookworld.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorInfoScheduler {
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final Logger LOG = LoggerFactory.getLogger(AuthorInfoScheduler.class);

    public AuthorInfoScheduler(BookService bookService, AuthorService authorService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }


    @Scheduled(fixedRate = 43200000, initialDelay = 300000) //per 12 hours and starts 5 minutes after the program is launched
    public void getBooksWithoutAuthorInfo() {
        List<Book> allBooks = bookService.getAllBooksForScheduling();

        allBooks
                .forEach(book -> {
                    Author author = this.authorService.getAuthorByName(book.getAuthor().getName());

                    if(book.getAuthor().getCountry() == null && book.getAuthor().getBirthDate() == null) {
                        author.setCountry("We are sorry for the delay. Our admins are doing their best.");
                        // book.getAuthor().setCountry("We are sorry for the delay. Our admins are doing their best.");
                        LOG.info("" + book.getAuthor().getName() + " country has not been added.");


                        author.setBirthDate("We are sorry for the delay. Our admins are doing their best.");
                        // book.getAuthor().setBirthDate("We are sorry for the delay. Our admins are doing their best.");
                        LOG.info("" + book.getAuthor().getName() + " birth date has not been added.");

                        this.authorRepository.saveAndFlush(author);
                    }

                });

        LOG.info("All authors have been revised.");
    }
}
