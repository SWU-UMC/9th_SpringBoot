package com.example.leeseo.domain.review.exception;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import com.example.leeseo.global.entity.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
