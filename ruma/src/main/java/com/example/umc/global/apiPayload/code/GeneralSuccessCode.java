package com.example.umc.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "COMMON_200", "성공적으로 처리되었습니다"),
    CREATED(HttpStatus.CREATED, "COMMON_201", "리소스가 생성되었습니다"),
    ACCEPTED(HttpStatus.ACCEPTED, "COMMON_202", "요청이 접수되었습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}


