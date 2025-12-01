package com.example.umc.domain.review.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 작성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
