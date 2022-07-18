package com.apistudy.service;

import com.apistudy.domain.User;
import com.apistudy.repository.UserRepository;
import com.apistudy.request.UserJoin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserJoin userJoin) {
        User user = User.builder()
                .userName(userJoin.getUserName())
                .loginId(userJoin.getLoginId())
                .password(userJoin.getPassword())
                .build();

        userRepository.save(user);
    }

}
