package com.apistudy.repository;

import com.apistudy.domain.User;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final EntityManager em;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return  em.createQuery("select u from User u", User.class).getResultList()
                .stream()
                .filter(u -> u.getLoginId().equals(loginId))
                .findFirst();
    }
}
