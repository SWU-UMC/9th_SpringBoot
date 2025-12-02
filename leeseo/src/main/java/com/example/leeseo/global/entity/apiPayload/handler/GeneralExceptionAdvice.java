package com.example.leeseo.global.entity.apiPayload.handler;

import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import com.example.leeseo.global.entity.apiPayload.code.GeneralErrorCode;
import com.example.leeseo.global.entity.apiPayload.exception.GeneralException;
import com.example.leeseo.global.entity.apiPayload.exception.PageValidateException;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

// 애플리케이션에서 발생하는 커스텀 예외를 처리
@RestControllerAdvice
public class GeneralExceptionAdvice {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handlerException(
            GeneralException ex
    ) {
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                        ex.getCode(),
                        null
                )
            );
    }

    // 입력값 검증 실패 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException (
            MethodArgumentNotValidException ex
    ){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),error.getDefaultMessage())
        );

        GeneralErrorCode code = GeneralErrorCode.VALID_FAIL;
        ApiResponse<Map<String, String>> errorResponse = ApiResponse.onFailure(code, errors);

        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    // 페이지 검증 실패
    @ExceptionHandler(PageValidateException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handlePageValidate(PageValidateException ex) {
        return ResponseEntity
                .status(GeneralErrorCode.VALID_FAIL.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.VALID_FAIL, ex.getErrors()));
    }


    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handlerException(
            Exception ex
    ) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                        code,
                        null
                )
            );
    }
}

