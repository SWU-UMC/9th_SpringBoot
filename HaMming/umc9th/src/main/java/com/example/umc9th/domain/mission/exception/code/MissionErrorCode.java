package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    // 400 Bad Request
    MISSION4000(HttpStatus.BAD_REQUEST, "MISSION4000", "이미 완료된 미션입니다."),
    MISSION4001(HttpStatus.BAD_REQUEST, "MISSION4001", "이미 진행 중인 미션입니다."),
    MISSION4002(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 수락한 미션입니다."),

    // 404 Not Found
    MISSION4040(HttpStatus.NOT_FOUND, "MISSION4040", "미션을 찾을 수 없습니다."),
    MISSION4041(HttpStatus.NOT_FOUND, "MISSION4041", "사용자의 미션이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
