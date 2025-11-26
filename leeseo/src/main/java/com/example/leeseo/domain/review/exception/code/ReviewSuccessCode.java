package com.example.leeseo.domain.review.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰 저장에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
