package com.apistudy.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLogin {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public UserLogin() {
    }

    @Builder
    public UserLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
