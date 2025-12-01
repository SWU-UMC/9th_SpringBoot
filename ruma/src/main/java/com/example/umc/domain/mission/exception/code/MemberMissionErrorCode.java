package com.example.umc.domain.mission.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBERMISSION404_1",
            "해당 사용자 미션을 찾지 못했습니다."),

    ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST,
            "MEMBERMISSION400_1",
            "이미 도전 중인 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
