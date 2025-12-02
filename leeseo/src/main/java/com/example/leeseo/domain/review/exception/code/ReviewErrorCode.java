package com.example.leeseo.domain.review.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    SAVE_FAIL(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "리뷰 등록에 실패했습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
                    "해당 리뷰를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
