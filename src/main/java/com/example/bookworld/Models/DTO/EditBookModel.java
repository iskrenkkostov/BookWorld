package com.example.bookworld.Models.DTO;

import com.example.bookworld.Models.Entities.Book;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EditBookModel {

    private String country;

    private String birthDate;


    public EditBookModel(String country, String birthDate) {
        this.country = country;
        this.birthDate = birthDate;
    }

    public EditBookModel() {
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
