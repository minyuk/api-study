package com.apistudy.controller;

import com.apistudy.domain.User;
import com.apistudy.exception.InvalidRequest;
import com.apistudy.request.UserJoin;
import com.apistudy.request.UserLogin;
import com.apistudy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public void login(@RequestBody @Valid UserLogin user, HttpServletRequest request) {
        User loginUser = userService.login(user);

        if (loginUser == null) {
            throw new InvalidRequest("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원정보 보관
        session.setAttribute("loginUser", loginUser);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}
