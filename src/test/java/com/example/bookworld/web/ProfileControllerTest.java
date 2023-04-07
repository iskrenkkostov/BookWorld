package com.example.bookworld.web;

import com.example.bookworld.Models.DTO.BookModel;
import com.example.bookworld.Models.Entities.Book;
import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.*;
import com.example.bookworld.service.BookService;
import com.example.bookworld.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;

    private final String USERNAME = "iskrata1";

    private UserService userService;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockUserRepository);
    }

    @Test
    @WithMockUser("iskrata1")
    void getProfileReturnsCorrectly() throws Exception {

        User user = new User();

        when(userService.findUserByUsername(USERNAME)).thenReturn(Optional.of(user));

        Optional<User> userByUsername = userService.findUserByUsername(USERNAME);
        Assertions.assertNotNull(userByUsername);

        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user"));
    }
}
