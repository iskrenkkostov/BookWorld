package com.example.bookworld.configs;

import com.example.bookworld.web.ShowUsernameInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final ShowUsernameInterceptor showUsernameInterceptor;

    public InterceptorConfiguration(ShowUsernameInterceptor showUsernameInterceptor) {
        this.showUsernameInterceptor = showUsernameInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(showUsernameInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
