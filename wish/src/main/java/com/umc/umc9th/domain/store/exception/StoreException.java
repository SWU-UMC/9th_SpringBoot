package com.umc.umc9th.domain.store.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc.umc9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
  public StoreException(BaseErrorCode code) {
    super(code);
  }
}