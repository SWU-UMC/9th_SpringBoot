package com.umc.umc9th.domain.mission.exception;

import com.umc.umc9th.global.apiPayload.code.BaseErrorCode;
import com.umc.umc9th.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

  public MissionException(BaseErrorCode code) {
    super(code);
  }
}
