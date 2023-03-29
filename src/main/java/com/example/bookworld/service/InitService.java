package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Condition;
import com.example.bookworld.Models.Entities.Genre;
import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.Models.Enums.ConditionType;
import com.example.bookworld.Models.Enums.GenreType;
import com.example.bookworld.Models.Enums.UserRole;
import com.example.bookworld.repository.ConditionRepository;
import com.example.bookworld.repository.GenreRepository;
import com.example.bookworld.repository.RoleRepository;
import com.example.bookworld.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GenreRepository genreRepository;
    private final ConditionRepository conditionRepository;
    private final PasswordEncoder passwordEncoder;

    private final String EXCELLENT_STRING = "In perfect condition";
    private  String GOOD_STRING = "Some signs of wear and tear or minor defects";
    private  String ACCEPTABLE_STRING = "The item is fairly worn but continues to function properly";


    @Autowired
    public InitService(UserRepository userRepository, RoleRepository roleRepository, GenreRepository genreRepository, ConditionRepository conditionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.genreRepository = genreRepository;
        this.conditionRepository = conditionRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initConditions();
        initGenres();
        initUsers();
    }

    public void initConditions() {

        if(conditionRepository.count() == 0) {

            Condition conditionExcellent = new Condition(ConditionType.EXCELLENT, EXCELLENT_STRING);
            Condition conditionGood = new Condition(ConditionType.GOOD, GOOD_STRING);
            Condition conditionAcceptable = new Condition(ConditionType.ACCEPTABLE, ACCEPTABLE_STRING);

            conditionRepository.saveAndFlush(conditionExcellent);
            conditionRepository.saveAndFlush(conditionGood);
            conditionRepository.saveAndFlush(conditionAcceptable);
        }

    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            Role user = new Role();
            Role admin = new Role();

            user.setName(UserRole.USER);
            user.setDescription("People with role USER can use the website as normal users!");

            admin.setName(UserRole.ADMIN);
            admin.setDescription("People with role ADMIN have more permissions than normal users and can use the website with advanced features!");

            roleRepository.saveAndFlush(user);
            roleRepository.saveAndFlush(admin);
        }
    }

    private void initGenres() {
        if (genreRepository.count() == 0) {
            Genre action = new Genre(GenreType.Action, new ArrayList<>());
            Genre adventure = new Genre(GenreType.Adventure, new ArrayList<>());
            Genre fantasy = new Genre(GenreType.Fantasy, new ArrayList<>());
            Genre roman = new Genre(GenreType.Roman, new ArrayList<>());
            Genre romance = new Genre(GenreType.Romance, new ArrayList<>());
            Genre sciFiction = new Genre(GenreType.Sci_fiction, new ArrayList<>());
            Genre thriller = new Genre(GenreType.Thriller, new ArrayList<>());

            genreRepository.saveAndFlush(action);
            genreRepository.saveAndFlush(adventure);
            genreRepository.saveAndFlush(fantasy);
            genreRepository.saveAndFlush(roman);
            genreRepository.saveAndFlush(romance);
            genreRepository.saveAndFlush(sciFiction);
            genreRepository.saveAndFlush(thriller);
        }
    }

    private void initUsers() {
        if(userRepository.count() == 0) {
            Role roleUser = roleRepository.getRoleByName(UserRole.USER);
            Role roleAdmin = roleRepository.getRoleByName(UserRole.ADMIN);

            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);

            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(roleAdmin);


            User user = new User("User", "Userov", "user123", passwordEncoder.encode("1234"),
                    "user123@gmail.com", "asdasdasd", userRoles , null, null);

            User admin = new User("Admin", "Adminov", "admin123", passwordEncoder.encode("1234"),
                    "admin123@gmail.com", "asdasdasd", adminRoles, null, null);

            userRepository.saveAndFlush(user);
            userRepository.saveAndFlush(admin);
        }
    }
}
