package com.apistudy.service;

import com.apistudy.domain.User;
import com.apistudy.exception.InvalidRequest;
import com.apistudy.repository.UserRepository;
import com.apistudy.request.UserJoin;
import com.apistudy.request.UserLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        UserJoin userJoin = UserJoin.builder()
                .userName("테스터")
                .loginId("tester")
                .password("1234")
                .build();

        //when
        userService.join(userJoin);

        //then
        assertEquals(3L, userRepository.count());
        User user = userRepository.findAll().get(2);
        assertEquals("테스터", user.getUserName());
        assertEquals("tester", user.getLoginId());
        assertEquals("1234", user.getPassword());
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        //회원
        UserLogin loginUser1 = UserLogin.builder()
                .loginId("minyuk")
                .password("qwer1!")
                .build();
        //비회원
        UserLogin loginUser2 = UserLogin.builder()
                .loginId("tester2")
                .password("4321")
                .build();

        //when
        User user1 = userService.login(loginUser1);
        User user2 = userService.login(loginUser2);

        //then
        //로그인 성공
        assertThat(user1).isNotNull();
        assertEquals("minyuk", loginUser1.getLoginId());
        assertEquals("qwer1!", loginUser1.getPassword());
        //로그인 실패 - 회원가입 x
        assertThat(user2).isNull();
    }
}