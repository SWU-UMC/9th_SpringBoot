package com.example.leeseo.domain.mission.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_FAIL(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "미션 생성에 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
