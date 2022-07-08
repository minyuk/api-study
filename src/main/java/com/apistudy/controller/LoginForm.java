package com.apistudy.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
