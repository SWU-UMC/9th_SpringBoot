package com.example.leeseo.domain.member.exception;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import com.example.leeseo.global.entity.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}