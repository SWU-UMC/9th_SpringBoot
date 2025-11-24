package com.example.umc9th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "미션 응답 DTO 컨테이너")
public class MissionResponseDto {

    @Getter
    @Builder
    @Schema(description = "미션 도전 결과 응답 DTO")
    public static class ChallengeResult {
        @Schema(description = "등록된 UserMission ID", example = "10")
        private Long userMissionId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "미션 목록 조회용 DTO")
    public static class MissionPreviewDto {
        @Schema(description = "미션 ID", example = "1")
        private Long id;

        @Schema(description = "미션 포인트", example = "500")
        private Long point;

        @Schema(description = "미션 조건", example = "음료 2잔 이상 주문")
        private String condition;

        @Schema(description = "마감 날짜", example = "2025-12-31")
        private LocalDate deadline;
    }
}