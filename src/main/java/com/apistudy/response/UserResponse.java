package com.apistudy.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String userName;

    @Builder
    public UserResponse(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
