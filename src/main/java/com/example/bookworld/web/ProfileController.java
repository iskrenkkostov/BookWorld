package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.DTO.ProfileModel;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.UserRepository;
import com.example.bookworld.service.BookService;
import com.example.bookworld.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookService bookService;

    public ProfileController(UserRepository userRepository, UserService userService, BookService bookService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getProfile(Principal principal, Model model) {
       // Optional<User> user = this.userRepository.findUserByUsername(principal.getName());
        Optional<User> user = this.userService.findUserByUsername(principal.getName());

        if(user.isPresent()) {
            ProfileModel profileModel = new ProfileModel(
                    user.get().getFirstName() + " " + user.get().getLastName(), user.get().getEmail(), user.get().getDescription()
            );

            model.addAttribute("user", profileModel);
        }

        return "profile";
    }


    @GetMapping("/myBooks")
    public String getMyBooksForSelling(Model model, Principal principal) {
        List<BookModel> myBooks = this.bookService.findMyBooks(principal.getName());
        model.addAttribute("books", myBooks);

        return "my-books";
    }
}
