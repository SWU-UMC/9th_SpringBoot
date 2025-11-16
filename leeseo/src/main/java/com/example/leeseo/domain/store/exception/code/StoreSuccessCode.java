package com.example.leeseo.domain.store.exception.code;

import com.example.leeseo.global.entity.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "STORE200_1",
                    "가게를 성공적으로 등록했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
