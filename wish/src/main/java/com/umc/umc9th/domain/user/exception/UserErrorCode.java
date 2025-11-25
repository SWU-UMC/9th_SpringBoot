package com.umc.umc9th.domain.user.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND,
      "USER404_1",
      "해당 사용자를 찾을 수 없습니다."),

  DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST,
      "USER400_1",
      "이미 사용 중인 이메일입니다."),

  INVALID_GENDER(HttpStatus.BAD_REQUEST,
      "USER400_2",
      "유효하지 않은 성별 값입니다."),

  ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND,
      "USER404_2",
      "해당 주소 정보를 찾을 수 없습니다."),

  CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,
      "USER404_3",
      "선호 카테고리 정보를 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}