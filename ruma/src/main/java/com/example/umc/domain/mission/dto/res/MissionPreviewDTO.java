package com.example.umc.domain.mission.dto.res;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MissionPreviewDTO (
        Long missionId,
        String description,
        int point,
        LocalDateTime deadline
) {}
