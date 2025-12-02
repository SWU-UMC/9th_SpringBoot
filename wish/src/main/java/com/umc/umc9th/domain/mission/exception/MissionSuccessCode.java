package com.umc.umc9th.domain.mission.exception;

import com.umc.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

  CHALLENGED(HttpStatus.CREATED,
      "MISSION201_1",
      "미션 도전을 시작했습니다."),

  FOUND(HttpStatus.OK,
      "MISSION200_1",
          "미션 목록 조회가 성공적으로 완료되었습니다."),

  MY_MISSION_FOUND(HttpStatus.OK,
      "MISSION200_2",
          "내 진행중인 미션 목록 조회가 성공적으로 완료되었습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
