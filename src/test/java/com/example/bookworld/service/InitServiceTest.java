package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Condition;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.repository.ConditionRepository;
import com.example.bookworld.repository.GenreRepository;
import com.example.bookworld.repository.RoleRepository;
import com.example.bookworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private GenreRepository mockGenreRepository;
    @Mock
    private ConditionRepository mockConditionRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private InitService initService;

    @BeforeEach
    void setUp() {
        initService = new InitService(mockUserRepository, mockRoleRepository, mockGenreRepository, mockConditionRepository, mockPasswordEncoder);
    }

    @Test
    void initConditionsCorrectly() {

        Condition condition1 = new Condition(ConditionType.EXCELLENT, "In perfect condition");
        Condition condition2 = new Condition(ConditionType.GOOD, "Some signs of wear and tear or minor defects");
        Condition condition3 = new Condition(ConditionType.ACCEPTABLE, "The item is fairly worn but continues to function properly");

        when(mockConditionRepository.count()).thenReturn(Long.valueOf(0));

        initService.initConditions();

//        verify(mockConditionRepository).saveAndFlush(any());
//        verify(mockConditionRepository).saveAndFlush(any());
//        verify(mockConditionRepository).saveAndFlush(any());
         verify(mockConditionRepository).saveAndFlush(condition1);
          verify(mockConditionRepository).saveAndFlush(condition2);
          verify(mockConditionRepository).saveAndFlush(condition3);
    }
}
