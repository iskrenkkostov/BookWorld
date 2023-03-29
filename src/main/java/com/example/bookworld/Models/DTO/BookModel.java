package com.example.bookworld.Models.DTO;

import com.example.bookworld.Models.Entities.Author;
import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Enums.GenreType;

public class BookModel {
    private String name;
    private Genre genre;
    private Author author;
    private Double price;

    public BookModel() {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
