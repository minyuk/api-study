package com.apistudy.controller;

import com.apistudy.domain.User;
import com.apistudy.login.argumentresolver.Login;
import com.apistudy.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public UserResponse home(@Login User loginUser) {
        if (loginUser == null) {
            return null;
        }

        return UserResponse.builder()
                .id(loginUser.getId())
                .userName(loginUser.getUserName())
                .build();
    }

}
