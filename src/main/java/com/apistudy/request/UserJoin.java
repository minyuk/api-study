package com.apistudy.request;

import com.apistudy.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserJoin {

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String passwordCheck;

    public UserJoin() {
    }

    @Builder
    public UserJoin(String userName, String loginId, String password, String passwordCheck) {
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }

    public void validate() {
        if (!password.equals(passwordCheck)) {
            throw new InvalidRequest("password", "비밀번호가 일치하지 않습니다.");
        }
    }
}
