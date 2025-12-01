package com.example.server_9th.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseSuccessCode {

    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");

    private final HttpStatus Status;
    private final String code;
    private final String message;
}
