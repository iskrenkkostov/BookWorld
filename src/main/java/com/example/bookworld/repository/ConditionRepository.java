package com.example.bookworld.repository;

import com.example.bookworld.Models.Entities.Condition;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.Models.Enums.GenreType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Condition getConditionByName(@NotNull ConditionType name);
}
