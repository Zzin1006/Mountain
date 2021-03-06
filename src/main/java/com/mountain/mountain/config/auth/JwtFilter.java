package com.mountain.mountain.config.auth;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

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

        log.info("0");
        FirebaseToken decodedToken;

        //토큰 받아와 검증
        try {

            String header = RequestUtil.getAuthorizationToken((request.getHeader("Authorization")));
            log.info("1");
            decodedToken = firebaseAuth.verifyIdToken(header);
            log.info("2");
        } catch(FirebaseAuthException | IllegalArgumentException e) {
            //ErrorMessage 응답 전송
            log.info("token verify exception: " + e.getMessage());
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            log.info("3");
            response.setContentType("application/json");
            log.info("4");
            response.getWriter().write("{\"code\":\"INVALID_TOKEN\",\"message\":\"" +e.getMessage()+"\"}");
            log.info("5");
            return;

        }

        // User를 가져와 SecurityContext에 저장
        try {
            UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid());
            log.info("7");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            log.info("8");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("9");
        } catch(NoSuchElementException | CustomException e) {
            log.info("user found exception : " + e.getMessage());
            //ErrorMessage 응답 전송
            response.setStatus(HttpStatus.SC_NOT_FOUND);
            log.info("10");
            response.setContentType("application/json");
            log.info("11");
            response.getWriter().write("{\"code\":\"USER_NOT_FOUND\"}");
            log.info("12");
            return;

        }


        //요청, 응답시 filter호출
        filterChain.doFilter(request, response);
        log.info("13");

    }

}
