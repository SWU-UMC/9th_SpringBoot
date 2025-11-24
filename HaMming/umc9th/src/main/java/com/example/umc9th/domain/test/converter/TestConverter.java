package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.res.TestResDTO;

public class TestConverter {

    // 객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }
    // 객체 -> DTO
    public static TestResDTO.Exception toExceptionDTO(Long flag) {
        return TestResDTO.Exception.builder()
                .testString(String.valueOf(flag))  //controller에서 long으로 사용 ->flag는 숫자 타입이니까 converter에서 변환해주기
                .build();
    }
}
