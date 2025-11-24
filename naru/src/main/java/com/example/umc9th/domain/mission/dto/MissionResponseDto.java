package com.example.umc9th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "미션 응답 DTO 컨테이너")
public class MissionResponseDto {

    @Getter
    @Builder
    @Schema(description = "미션 도전 결과 응답 DTO")
    public static class ChallengeResult {
        @Schema(description = "등록된 UserMission ID", example = "10")
        private Long userMissionId;
    }
}