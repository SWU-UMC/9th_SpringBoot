package com.example.umc9th.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원가입 성공 응답 DTO")
    public static class JoinResultDto {
        @Schema(description = "회원 ID")
        private Long userId;
        @Schema(description = "가입 일시")
        private LocalDateTime createdAt;
    }
}