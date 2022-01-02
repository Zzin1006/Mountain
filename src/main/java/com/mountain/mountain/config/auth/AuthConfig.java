package com.mountain.mountain.config.auth;


import com.mountain.mountain.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AuthConfig {

    @Autowired
    private final UserService userService;

    @Bean
    @Profile("local")
    public AuthFilterContainer mockAuthFilter() {
        log.info("Initializing Mock AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new MockAuthFilter(userService));
        return authFilterContainer;
    }


}
