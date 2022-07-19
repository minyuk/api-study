package com.apistudy.controller;

import com.apistudy.domain.User;
import com.apistudy.exception.InvalidRequest;
import com.apistudy.repository.UserRepository;
import com.apistudy.request.UserJoin;
import com.apistudy.request.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("/join 요청 시 loginId 값은 필수다.")
    void blank() throws Exception {

        //given
        UserJoin request = UserJoin.builder()
                .userName("테스터")
                .password("1234")
                .passwordCheck("1234")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockmvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.loginId").value("아이디를 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 - 비밀번호 불일치")
    void passwordCheck() throws Exception{
        //given
        UserJoin request = UserJoin.builder()
                .userName("테스터")
                .loginId("tester")
                .password("1234")
                .passwordCheck("12345")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockmvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("/join 요청 시 DB에 저장")
    void join() throws Exception {

        //given
        UserJoin request = UserJoin.builder()
                .userName("테스터")
                .loginId("tester")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockmvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/login 요청 시 로그인")
    void login() throws Exception {

        //given
        UserLogin request = UserLogin.builder()
                .loginId("minyuk")
                .password("qwer1!")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockmvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }



}