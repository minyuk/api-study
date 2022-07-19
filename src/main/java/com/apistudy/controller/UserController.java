package com.apistudy.controller;

import com.apistudy.request.UserJoin;
import com.apistudy.request.UserLogin;
import com.apistudy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(@RequestBody @Valid UserJoin request) {
        //비밀번호 일치 여부 검증
        request.validate();
        userService.join(request);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid UserLogin request) {
        userService.login(request);
    }
}
