package com.example.bookworld.service;

import com.example.bookworld.Models.Entities.Role;
import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.Models.Enums.UserRole;
import com.example.bookworld.repository.RoleRepository;
import com.example.bookworld.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InitService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public InitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
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

    private void initUsers() {
        if(userRepository.count() == 0) {
            Role roleUser = roleRepository.getRoleByName(UserRole.USER);
            Role roleAdmin = roleRepository.getRoleByName(UserRole.ADMIN);


            User user = new User("User", "Userov", "user123", passwordEncoder.encode("1234"),
                    "user123@gmail.com", "asdasdasd", (Set<Role>) roleUser, null, null);

            User admin = new User("Admin", "Adminov", "admin123", passwordEncoder.encode("1234"),
                    "admin123@gmail.com", "asdasdasd", (Set<Role>) roleAdmin, null, null);

            userRepository.saveAndFlush(user);
            userRepository.saveAndFlush(admin);
        }
    }
}
