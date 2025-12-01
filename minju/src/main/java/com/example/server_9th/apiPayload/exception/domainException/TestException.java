package com.example.server_9th.apiPayload.exception.domainException;

import com.example.server_9th.apiPayload.code.BaseErrorCode;
import com.example.server_9th.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
