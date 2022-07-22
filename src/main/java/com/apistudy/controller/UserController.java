package com.apistudy.controller;

import com.apistudy.domain.User;
import com.apistudy.exception.InvalidRequest;
import com.apistudy.request.UserJoin;
import com.apistudy.request.UserLogin;
import com.apistudy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

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

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/kakao/login")
    public String kakaoCallback(String code) {

        //POST 방식으로 key = value 데이터를 요청 (카카오쪽으로)
        RestTemplate restTemplate = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);

        //HttpBody 오브젝트 생성
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "7b50c8cfb314efb023fa626a6919a0b5");
        params.add("redirect_uri", "http://localhost:8080/kakao/login");
        params.add("code", code);
        params.add("client_secret", "");

        //HttpHeader와 HttpBody를 하나의 오브젝트의 담기
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        //Http 요청하기 - Post 방식 - response 변수의 응답 받기
        String url = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return "카카오 인증 완료 : 토큰 요청에 대한 응답 = " + response;
    }
}
