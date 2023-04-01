package com.example.bookworld.Models.DTO;

import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.Models.Enums.GenreType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBookModel {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 5)
    private String author;

    @NotNull
    @Min(5)
    private double price;

    @NotNull
    private GenreType genre;

    @NotNull
    private ConditionType condition;

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }

    public CreateBookModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
