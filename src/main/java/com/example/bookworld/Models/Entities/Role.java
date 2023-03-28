package com.example.bookworld.Models.Entities;

import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.Models.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Column(unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole name;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;

    public Role() {
    }

    public Role(UserRole name, String description) {
        this.name = name;
        this.description = description;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
