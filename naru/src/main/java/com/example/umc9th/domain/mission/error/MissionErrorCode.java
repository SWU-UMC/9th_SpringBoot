package com.example.umc9th.domain.mission.error;

import com.example.umc9th.global.entity.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "요청하신 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION4002", "이미 도전 중인 미션입니다. 중복 도전할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}