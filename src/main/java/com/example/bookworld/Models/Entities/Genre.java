package com.example.bookworld.Models.Entities;

import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.Models.Enums.GenreType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity{

    @Column(unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private GenreType name;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(GenreType name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Genre() {

    }

    public GenreType getName() {
        return name;
    }

    public void setName(GenreType name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
