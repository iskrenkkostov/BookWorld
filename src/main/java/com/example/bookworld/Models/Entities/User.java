package com.example.bookworld.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    private String description;

    @ManyToOne
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "seller")
    private List<Book> booksToSell;

    @OneToMany(mappedBy = "buyer")
    private List<Book> boughtBooks;

    public User() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooksToSell() {
        return booksToSell;
    }

    public void setBooksToSell(List<Book> booksToSell) {
        this.booksToSell = booksToSell;
    }

    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }

    public void setBoughtBooks(List<Book> boughtBooks) {
        this.boughtBooks = boughtBooks;
    }
}
