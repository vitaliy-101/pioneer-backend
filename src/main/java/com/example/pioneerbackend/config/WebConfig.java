package com.example.pioneerbackend.config;

import com.example.pioneerbackend.resolver.UserUuidArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserUuidArgumentResolver userUuidArgumentResolver;

    public WebConfig(UserUuidArgumentResolver userUuidArgumentResolver) {
        this.userUuidArgumentResolver = userUuidArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userUuidArgumentResolver);
    }
}