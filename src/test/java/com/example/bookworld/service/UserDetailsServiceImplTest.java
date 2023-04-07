package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userService;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockUserRepository);
        userDetailsService = new UserDetailsServiceImpl(userService);
    }

    @Test
    void userFoundCorrectly() {
        String username = "iskrata1";
        String password = "1234";
        List<Role> roleList = new ArrayList<>();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roleList);

        when(mockUserRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
//        when(userService.getUserByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Assertions.assertEquals(user.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(user.getPassword(), userDetails.getPassword());

    }


}
