package com.example.umc9th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "미션 요청 DTO 컨테이너")
public class MissionRequestDto {

    @Getter
    @Schema(description = "미션 도전 요청 DTO")
    public static class ChallengeMission {

        @NotNull(message = "사용자 ID는 필수입니다.")
        @Schema(description = "미션에 도전할 사용자 ID", example = "1")
        private Long userId;

        @NotNull(message = "미션 ID는 필수입니다.")
        @Schema(description = "도전할 미션 ID", example = "5")
        private Long missionId;
    }
}