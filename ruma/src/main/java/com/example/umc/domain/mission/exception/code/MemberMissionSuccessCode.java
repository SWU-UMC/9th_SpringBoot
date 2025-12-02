package com.example.umc.domain.mission.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CHALLENGED(HttpStatus.CREATED,
            "MEMBERMISSION201_1",
            "미션 도전이 완료되었습니다."),

    MY_MISSION_LIST_SUCCESS(HttpStatus.OK,
            "MEMBERMISSION200_2",
            "내가 진행 중인 미션 목록을 성공적으로 조회했습니다."),


    FOUND(HttpStatus.OK,
            "MEMBERMISSION200_1",
            "성공적으로 미션을 조회했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
