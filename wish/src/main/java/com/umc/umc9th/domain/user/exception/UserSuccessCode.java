package com.umc.umc9th.domain.user.exception;

import com.umc.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

  CREATED(HttpStatus.CREATED,
      "USER201_1",
      "회원가입이 성공적으로 완료되었습니다."),

  FOUND(HttpStatus.OK,
      "USER200_1",
      "사용자 정보를 성공적으로 조회했습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}