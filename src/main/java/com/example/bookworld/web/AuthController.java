package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.RegisterModel;
import com.example.bookworld.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "registerModel") RegisterModel registerModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", registerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerModel",
                            bindingResult);

            return "redirect:register";
        }

        authService.registerUser(registerModel);
        return "login";
    }

    @ModelAttribute(name = "registerModel")
    public RegisterModel registerModel() {
        return new RegisterModel();
    }

    //exceptions


}

