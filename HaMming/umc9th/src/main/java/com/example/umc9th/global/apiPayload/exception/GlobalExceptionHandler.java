package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException ex) {
        BaseErrorCode errorCode = ex.getCode();

        log.warn("[GeneralException] code={}, message={}", errorCode.getCode(), errorCode.getMessage());

        ApiResponse<Object> body = ApiResponse.onFailure(errorCode, null);
        return new ResponseEntity<>(body, errorCode.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        log.error("[Unhandled Exception]", ex);

        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        ApiResponse<Object> body = ApiResponse.onFailure(errorCode, null);
        return new ResponseEntity<>(body, errorCode.getStatus());
    }
}
