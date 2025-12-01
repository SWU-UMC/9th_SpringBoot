package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MissionDto {

    private Long missionId;
    private String description;
    private Integer point;
    private LocalDateTime deadline;

    private boolean alreadyAccepted; // 이미 수락한 미션인지
}
