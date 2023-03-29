package com.example.bookworld.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "seller")
    private List<Book> booksToSell;

    @OneToMany(mappedBy = "buyer")
    private List<Book> boughtBooks;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, String email, String description, List<Role> roles, List<Book> booksToSell, List<Book> boughtBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
        this.roles = roles;
        this.booksToSell = booksToSell;
        this.boughtBooks = boughtBooks;
    }

    public User(String username, String password, Role role) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
