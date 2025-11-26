package com.example.leeseo.domain.member.exception;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import com.example.leeseo.global.entity.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
