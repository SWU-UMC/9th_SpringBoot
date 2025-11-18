package com.example.umc.domain.store.exception.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "가게를 성공적으로 찾았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
