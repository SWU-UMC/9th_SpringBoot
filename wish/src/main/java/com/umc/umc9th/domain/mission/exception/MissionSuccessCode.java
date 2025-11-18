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
      "미션 도전을 시작했습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
