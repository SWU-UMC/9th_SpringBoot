package com.example.umc9th.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AuthResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "로그인 성공 응답 DTO")
    public static class LoginResultDto {
        @Schema(description = "회원 ID")
        private Long userId;

        @Schema(description = "JWT 액세스 토큰")
        private String accessToken;

        @Schema(description = "JWT 리프레시 토큰")
        private String refreshToken;
    }
}