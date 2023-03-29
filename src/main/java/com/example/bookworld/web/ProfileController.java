package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.ProfileModel;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String getProfile(Principal principal, Model model) {
        Optional<User> user = this.userRepository.findUserByUsername(principal.getName());

        if(user.isPresent()) {
            ProfileModel profileModel = new ProfileModel(
                    user.get().getFirstName() + " " + user.get().getLastName(), user.get().getEmail(), user.get().getDescription()
            );

            model.addAttribute("user", profileModel);
        }

        return "profile";
    }
}
