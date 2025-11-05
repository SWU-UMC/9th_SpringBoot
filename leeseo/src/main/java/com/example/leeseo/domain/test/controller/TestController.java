package com.example.leeseo.domain.test.controller;

import com.example.leeseo.domain.test.converter.TestConverter;
import com.example.leeseo.domain.test.dto.res.TestResDTO;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import com.example.leeseo.global.entity.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception {
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("This is Test!")
        );
    }
}
