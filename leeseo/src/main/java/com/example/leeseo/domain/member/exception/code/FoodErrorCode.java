package com.example.leeseo.domain.member.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "해당 음식 타입을 찾지 못했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
