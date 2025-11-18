package com.umc.umc9th.domain.review.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "유저를 찾을 수 없습니다."),
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "가게를 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}