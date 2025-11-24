package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK("SUCCESS200_1", "요청이 성공적으로 처리되었습니다."),
    CREATED("SUCCESS201_1", "리소스가 성공적으로 생성되었습니다."),
    SUCCESS("SUCCESS200_0", "성공");

    private final String code;
    private final String message;
}
