package com.mountain.mountain.config;

import com.mountain.mountain.config.auth.AuthFilterContainer;
import com.mountain.mountain.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    private AuthFilterContainer authFilterContainer;

    public void configure(WebSecurity web) throws Exception {
        //인증 예외 URL 설정

        web.ignoring().antMatchers(HttpMethod.POST,"users")
                .antMatchers("/");
    }
}
