package com.apistudy.service;

import com.apistudy.domain.User;
import com.apistudy.repository.UserRepository;
import com.apistudy.request.UserJoin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
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
        assertEquals(1L, userRepository.count());
        User user = userRepository.findAll().get(0);
        assertEquals("테스터", user.getUserName());
        assertEquals("tester", user.getLoginId());
        assertEquals("1234", user.getPassword());
    }
}