package com.example.umc9th.domain.auth.controller;

import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.auth.service.AuthService;
import com.example.umc9th.global.entity.apiPayload.ApiResponse;
import com.example.umc9th.global.entity.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "로그인 및 인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다.")
    public ApiResponse<AuthResponseDto.LoginResultDto> login(
            @RequestBody @Valid AuthRequestDto.LoginDto request
    )
    {
        AuthResponseDto.LoginResultDto result = authService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}