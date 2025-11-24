package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP_SUCCESS("MEMBER201_1", "회원가입이 성공적으로 완료되었습니다."),
    SOCIAL_SIGN_UP_SUCCESS("MEMBER201_2", "소셜 회원가입이 성공적으로 완료되었습니다.");

    private final String code;
    private final String message;
}
