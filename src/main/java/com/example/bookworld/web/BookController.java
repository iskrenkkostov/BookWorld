package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.CreateBookModel;
import com.example.bookworld.Models.DTO.EditBookModel;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final UserService userService;
    private final ConditionService conditionService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, UserService userService, ConditionService conditionService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.userService = userService;
        this.conditionService = conditionService;
        this.genreService = genreService;
    }


    @GetMapping("/create")
    public String createBook() {
        return "create-book";
    }

    @GetMapping("/getAll")
    public String getAll(Model model, Principal principal) {
        List<BookModel> otherBooks = this.bookService.getAllBooksWithoutBuyer(principal.getName());
        model.addAttribute("books", otherBooks);

        return "all-books";
    }

    @PostMapping("/create")
    public String postCreateBooks(@Valid @ModelAttribute(name = "createBookModel") CreateBookModel createBookModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createBookModel", createBookModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.createBookModel",
                            bindingResult);

            return "redirect:create";
        }



        authorService.saveAuthor(createBookModel.getAuthor());
        bookService.createBookOffer(createBookModel, principal.getName());

        return "redirect:getAll";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteBook(id);

        return "all-books";
    }

    @GetMapping("/{id}/details")
    public String getDetails(@PathVariable Long id, Model model) {
        BookModel book = this.bookService.findBookById(id);

        model.addAttribute("book", book);
        return "book-detail";
    }

    @ModelAttribute(name = "createBookModel")
    public CreateBookModel registerModel() {
        return new CreateBookModel();
    }


    @GetMapping("/edit/{id}")
    public String getEditAuthor(@PathVariable Long id, Model model) {

        BookModel book = this.bookService.findBookById(id);


        model.addAttribute("book", book);
        return "book-edit";
    }

    @PostMapping("/edit/{id}")
    public String postEditAuthor(@PathVariable Long id,  @ModelAttribute(name = "editBookModel") EditBookModel editBookModel, Model model) {

        BookModel bookToShow = this.bookService.addCountryAndBirthDateToAuthor(id, editBookModel);
        model.addAttribute("book", bookToShow);

        return "book-detail";
    }

    @ModelAttribute(name = "editBookModel")
    public EditBookModel editBookModelModel() {
        return new EditBookModel();
    }

    @GetMapping("/{id}/buy")
    public String buyBook(@PathVariable Long id, Principal principal) {
        this.bookService.buyBook(id, principal.getName());

        return "redirect:/books/getAll";
    }

}