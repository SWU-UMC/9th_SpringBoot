package com.example.leeseo.global.entity.apiPayload.exception;

import java.util.Map;

public class PageValidateException extends RuntimeException {
    private final Map<String, String> errors;

    public PageValidateException(Map<String, String> errors) {
        super("Page validation failed");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

