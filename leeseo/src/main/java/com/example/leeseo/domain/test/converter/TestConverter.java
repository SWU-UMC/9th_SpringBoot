package com.example.leeseo.domain.test.converter;

import com.example.leeseo.domain.test.dto.res.TestResDTO;

public class TestConverter {
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }
}
