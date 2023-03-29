package com.example.bookworld.repository;

import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Enums.GenreType;
import com.example.bookworld.Models.Enums.UserRole;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre getGenreByName(@NotNull GenreType name);
}
