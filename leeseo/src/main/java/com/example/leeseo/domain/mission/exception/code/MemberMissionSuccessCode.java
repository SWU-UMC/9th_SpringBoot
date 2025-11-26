package com.example.leeseo.domain.mission.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MEMBER_MISSION200_1",
            "유저미션 등록에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
