package com.example.bookworld.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLoginReturnsCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void getRegisterReturnsCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void postRegisterReturnsCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .param("username", "blabla")
                        .param("password", "1234")
                        .param("email", "blablaa23@gmail.com")
                        .param("firstName", "Denis")
                        .param("lastName", "Mihaylov")
                        .param("description", "Yound and motivated to read all books in the world.")
                .with(csrf()))
                .andExpect(status().isOk()).andExpect(view().name("login"));


    }

    @Test
    void postRegisterReturnsWrong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .param("username", "blablablablabla")
                .param("password", "1234")
                .param("email", "blablablablabla123@gmail.com")
                .param("firstName", "")
                .param("lastName", "Mihaylov")
                .param("description", "Yound and motivated to read all books in the world.")
                .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("register"));
    }
}
