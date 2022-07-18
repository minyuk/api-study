package com.apistudy.exception;

import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class ApiException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatus();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
