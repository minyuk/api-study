package com.apistudy.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLogin {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public UserLogin() {
    }

    @Builder
    public UserLogin(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
