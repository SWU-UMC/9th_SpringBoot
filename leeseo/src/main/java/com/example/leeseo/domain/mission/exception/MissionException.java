package com.example.leeseo.domain.mission.exception;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import com.example.leeseo.global.entity.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
