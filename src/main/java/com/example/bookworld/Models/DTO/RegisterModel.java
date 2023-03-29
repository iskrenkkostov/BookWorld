package com.example.bookworld.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterModel {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    @Size(min = 2, max = 20)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 20)
    @NotNull
    private String lastName;

    @Size(min = 10)
    @NotNull
    private String description;

    public RegisterModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
