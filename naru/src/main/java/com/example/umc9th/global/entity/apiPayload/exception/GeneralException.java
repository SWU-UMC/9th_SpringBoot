package com.example.umc9th.global.entity.apiPayload.exception;

import com.example.umc9th.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private final BaseErrorCode code;
}