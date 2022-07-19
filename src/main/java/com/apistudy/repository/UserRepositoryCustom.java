package com.apistudy.repository;

import com.apistudy.domain.User;

import java.util.Optional;

public interface UserRepositoryCustom {

    Optional<User> findByLoginId(String loginId);
}
