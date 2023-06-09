package com.example.bookworld.Models.Entities;

import com.example.bookworld.Models.Enums.GenreType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column
    @NotNull
    private String name;

    @ManyToOne
    private Author author;

    @Column(precision = 10)
    @NotNull
    private double price;

    @ManyToOne
    @NotNull
    private Condition condition;

    @ManyToOne()
    @NotNull
    private Genre genre;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
