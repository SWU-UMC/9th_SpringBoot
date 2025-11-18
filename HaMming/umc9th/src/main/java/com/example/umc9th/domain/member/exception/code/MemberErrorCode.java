package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 사용중인 이메일입니다."),
    NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "MEMBER400_2", "이미 사용중인 닉네임입니다."),
    SOCIAL_ID_DUPLICATED(HttpStatus.BAD_REQUEST, "MEMBER400_3", "이미 존재하는 소셜 계정입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
