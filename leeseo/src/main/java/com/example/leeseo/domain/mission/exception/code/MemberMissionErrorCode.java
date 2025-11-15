package com.example.leeseo.domain.mission.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {
    ALREADY_JOINED(HttpStatus.BAD_REQUEST,
            "MEMBER_MISSION400_1",
            "이미 진행중인 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
