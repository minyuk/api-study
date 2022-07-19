package com.apistudy;

import com.apistudy.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            User user = User.builder()
                    .userName("관리자")
                    .loginId("admin")
                    .password("1357")
                    .build();
            em.persist(user);
        }

        public void dbInit2() {
            User user = User.builder()
                    .userName("미녁")
                    .loginId("minyuk")
                    .password("qwer1!")
                    .build();
            em.persist(user);
        }
    }
}
