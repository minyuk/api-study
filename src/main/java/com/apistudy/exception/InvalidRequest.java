package com.apistudy.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends ApiException{

    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatus() {
        return 400;
    }
}
