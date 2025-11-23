package com.example.leeseo.global.entity.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PageErrorCode implements BaseErrorCode{
    VALID_FAIL(HttpStatus.METHOD_NOT_ALLOWED,
            "VALID400_1",
            "유효하지 않은 페이지 번호입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
