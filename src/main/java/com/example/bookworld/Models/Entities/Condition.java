package com.example.bookworld.Models.Entities;

import com.example.bookworld.Models.Enums.ConditionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{

    @Column(unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ConditionType name;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    public Condition() {
    }

    public Condition(ConditionType name, String description) {
        this.name = name;
        this.description = description;
    }

    public ConditionType getName() {
        return name;
    }

    public void setName(ConditionType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
