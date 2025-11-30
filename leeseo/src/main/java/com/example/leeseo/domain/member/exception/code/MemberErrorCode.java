package com.example.leeseo.domain.member.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    INVALID_PW(HttpStatus.METHOD_NOT_ALLOWED,
            "VALID400_1",
            "비밀번호가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}