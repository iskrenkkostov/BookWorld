package com.example.bookworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping("/create")
    public String createBook() {
        return "create-book";
    }

    @GetMapping("/getAll")
    public String getAll() {
        return "all-books";
    }
}
