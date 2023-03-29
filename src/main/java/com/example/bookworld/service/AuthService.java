package com.example.bookworld.service;

import com.example.bookworld.Models.DTO.RegisterModel;
import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.Models.Enums.UserRole;
import com.example.bookworld.repository.RoleRepository;
import com.example.bookworld.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(RegisterModel registerModel) {
            User userToRegister = modelMapper.map(registerModel, User.class);
            userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));

            List<Role> roles = new ArrayList<>();

            if(userRepository.count() > 0) {

                Role roleUser = roleRepository.getRoleByName(UserRole.USER);
                roles.add(roleUser);
                userToRegister.setRoles(roles);
            } else {

                Role roleAdmin = roleRepository.getRoleByName(UserRole.ADMIN);
                roles.add(roleAdmin);
                userToRegister.setRoles(roles);
            }

            this.userRepository.saveAndFlush(userToRegister);
        }
    }
