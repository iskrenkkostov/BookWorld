package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public Optional<User> findUserByUsername(String name) {

        return this.userRepository.findUserByUsername(name);
    }
}
