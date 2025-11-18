package com.umc.umc9th.domain.mission.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_0", "유저를 찾을 수 없습니다."),
  MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾을 수 없습니다."),
  ALREADY_IN_PROGRESS(HttpStatus.CONFLICT, "MISSION409_1", "이미 도전 중인 미션입니다."),
  MISSION_INACTIVE(HttpStatus.BAD_REQUEST, "MISSION400_1", "현재 진행할 수 없는 미션입니다."),
  MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION400_2", "미션 마감일이 지났습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
