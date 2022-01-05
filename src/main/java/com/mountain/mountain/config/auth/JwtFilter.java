package com.mountain.mountain.config.auth;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.mountain.mountain.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private FirebaseAuth firebaseAuth;


    public JwtFilter(UserDetailsService userDetailsService, FirebaseAuth firebaseAuth) {
        this.userDetailsService = userDetailsService;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        FirebaseToken decodedToken;

        //토큰 받아와 검증
        try {

            String header = RequestUtil.getAuthorizationToken((request.getHeader("Authorization")));
            decodedToken = firebaseAuth.verifyIdToken(header);
        } catch(FirebaseAuthException | IllegalArgumentException e) {
            //ErrorMessage 응답 전송
            log.info("token verify exception: " + e.getMessage());
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":\"INVALID_TOKEN\",\"message\":\"" +e.getMessage()+"\"}");
            return;

        }

        //요청, 응답시 filter호출
        filterChain.doFilter(request, response);

    }

}
