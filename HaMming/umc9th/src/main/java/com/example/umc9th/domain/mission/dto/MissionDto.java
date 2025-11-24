package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionDto {

    private Long missionId;
    private String missionSpec;
    private Integer point;

    public static MissionDto from(Mission mission) {
        return MissionDto.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getDescription())
                .point(mission.getPoint())
                .build();
    }
}
