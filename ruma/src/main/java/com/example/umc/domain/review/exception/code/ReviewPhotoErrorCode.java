package com.example.umc.domain.review.exception.code;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewPhotoErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEWPHOTO404_1",
            "해당 리뷰 사진을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
