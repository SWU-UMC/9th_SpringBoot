package com.example.server_9th.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResDTO {
    // 회원가입 응답 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDTO {
        private Long memberId;      // 생성된 회원 ID
        private LocalDateTime createdAt; // 가입 일시
    }

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken,
            LocalDateTime expirationTime
    ){}
}
