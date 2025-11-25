package com.umc.umc9th.domain.review.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc.umc9th.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
  public ReviewException(BaseErrorCode code) {
    super(code);
  }
}