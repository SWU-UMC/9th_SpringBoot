package com.umc.umc9th.domain.user.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc.umc9th.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException {
  public UserException(BaseErrorCode code) {
    super(code);
  }
}
