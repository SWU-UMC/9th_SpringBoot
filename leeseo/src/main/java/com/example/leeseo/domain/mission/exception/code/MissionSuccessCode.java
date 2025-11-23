package com.example.leeseo.domain.mission.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    POST_OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 등록에 성공했습니다."),
    GET_OK(HttpStatus.OK,
            "MISSION200_2",
            "미션 조회에 성공했습니다."),
    PATCH_OK(HttpStatus.OK,
            "MISSION200_3",
            "상태 변경에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
