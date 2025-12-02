package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;
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

    public static MissionDto from(Mission mission) {
        return MissionDto.builder()
                .missionId(mission.getId())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())  // null 가능 구조 그대로 반영
                .alreadyAccepted(false)
                .build();
    }
}
