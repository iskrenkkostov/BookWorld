package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.CreateBookModel;
import com.example.bookworld.service.AuthorService;
import com.example.bookworld.service.BookService;
import com.example.bookworld.service.ConditionService;
import com.example.bookworld.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ConditionService conditionService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, ConditionService conditionService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.conditionService = conditionService;
        this.genreService = genreService;
    }


    @GetMapping("/create")
    public String createBook() {
        return "create-book";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<BookModel> allBooks = this.bookService.getAllBooks();
        model.addAttribute("books", allBooks);

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

    @ModelAttribute(name = "createBookModel")
    public CreateBookModel registerModel() {
        return new CreateBookModel();
    }
}
