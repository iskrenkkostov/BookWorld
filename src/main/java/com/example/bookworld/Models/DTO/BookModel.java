package com.example.bookworld.Models.DTO;

import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.Models.Entities.Condition;
import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.Models.Enums.GenreType;

public class BookModel {
    private Long id;
    private String name;
    private Genre genre;
    private Author author;
    private Double price;
    private Condition condition;
    private User seller;

    public BookModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
