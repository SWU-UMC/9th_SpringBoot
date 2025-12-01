package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // 400 Bad Request
    REVIEW4000(HttpStatus.BAD_REQUEST, "REVIEW4000", "이미 삭제된 리뷰입니다."),
    REVIEW4001(HttpStatus.BAD_REQUEST, "REVIEW4001", "해당 리뷰에 접근할 권한이 없습니다."),

    // 404 Not Found
    REVIEW4040(HttpStatus.NOT_FOUND, "REVIEW4040", "리뷰를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
