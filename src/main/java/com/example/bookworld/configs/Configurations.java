package com.example.bookworld.configs;

import com.example.bookworld.repository.UserRepository;
import com.example.bookworld.service.UserDetailsServiceImpl;
import com.example.bookworld.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configurations {
    private final UserService userService;
    private final  UserRepository userRepository;

    public Configurations(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/books/getAll").permitAll().
                requestMatchers("/auth/register", "/auth/login", "/auth/login/error").anonymous().
        // requestMatchers("/books/create").hasRole("ADMIN").
                requestMatchers("/profile").authenticated().
                requestMatchers("/books/create").authenticated().
               // anyRequest().authenticated().
        and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/users/login?error=true")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .and()
                .rememberMe()
                .key("someUniqueKey")
                .tokenValiditySeconds(604800)
                .userDetailsService(new UserDetailsServiceImpl(new UserService(userRepository)));

//        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userService);
    }

}
