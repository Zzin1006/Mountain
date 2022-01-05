package com.mountain.mountain.controller.user;


import com.mountain.mountain.controller.user.dto.RegisterUserDTO;
import com.mountain.mountain.controller.user.dto.UserDTO;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.domain.user.service.UserService;
import com.mountain.mountain.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //회원 등록
    @PostMapping("")
    public void register(@RequestHeader("Authorization") String authorization,
                         @RequestBody RegisterUserDTO registerUserDTO) {

        String uid;

        try {
        String token = RequestUtil.getAuthorizationToken(authorization);

            //테스트 시 검증 스킵
            if (token.startsWith("test")){

                //랜덤 스트링을 생성해 uid 생성
                int leftLimit = 97;
                int rightLimit = 122;
                int length = 10;
                Random random = new Random();
                String generatedString = random.ints(leftLimit,rightLimit + 1)
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                        
                uid = generatedString;

                userService.register(uid, registerUserDTO);

            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }


    }


    // 로그인
    @GetMapping("/me")
    public UserDTO login(Authentication authentication) {
        User loginUser = (User) authentication.getPrincipal();
        return new UserDTO(loginUser);
    }


}