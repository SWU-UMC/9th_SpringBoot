package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionConverter {

    public MissionDto toMissionDto(Mission mission, boolean alreadyAccepted) {
        return MissionDto.builder()
                .missionId(mission.getId())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .alreadyAccepted(alreadyAccepted)
                .build();
    }
}
