package com.example.bookworld.repository;

import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Enums.UserRole;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(@NotNull UserRole name);
}
