package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockUserRepository);
    }

    @Test
    void getUserByUsernameCorrect() {
        String username = "iskrata1";

        User user = new User();

        when(mockUserRepository.findUserByUsername(username)).thenReturn(Optional.of(user));

        User userByUsername = userService.getUserByUsername(username);

        Assertions.assertNotNull(userByUsername);
    }

    @Test
    void getUserByUsernameError() {
        String username = "non-existent";

        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByUsername(username));
    }

    @Test
    void findUserByUsernameCorrectly() {
        String username = "iskrata1";

        User user = new User();

        when(mockUserRepository.findUserByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> userByUsername = userService.findUserByUsername(username);

        Assertions.assertNotNull(userByUsername);
    }
}
