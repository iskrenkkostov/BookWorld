package com.example.bookworld.service;

import com.example.bookworld.Models.DTO.RegisterModel;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.repository.RoleRepository;
import com.example.bookworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(mockUserRepository, mockRoleRepository, mockModelMapper, mockPasswordEncoder);
    }

    @Test
    void registerUserCorrectly() {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setFirstName("Kaloyan");
        registerModel.setLastName("Ilchev");
        registerModel.setUsername("kalata123");
        registerModel.setEmail("kalata123@gmail.com");
        registerModel.setDescription("asfdagadgadsfsadfasdsadsagadgasg");
        registerModel.setPassword("1234");

        when(mockPasswordEncoder.encode(registerModel.getPassword())).thenReturn("SD5E20FKMWPLKM4E2FD");

        when(mockModelMapper.map(registerModel, User.class)).
                thenReturn(new User(registerModel.getFirstName(), registerModel.getLastName(), registerModel.getUsername(), registerModel.getPassword(),
                        registerModel.getEmail(), registerModel.getDescription(), List.of(), List.of(), List.of()));

        authService.registerUser(registerModel);

        verify(mockUserRepository).saveAndFlush(any());

        // register second user

        when(mockUserRepository.count()).thenReturn(Long.valueOf(1));

        authService.registerUser(registerModel);

    }
}
