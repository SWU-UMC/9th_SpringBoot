package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberMissionDto {

    private Long memberMissionId;
    private Long missionId;
    private String missionSpec;
    private Integer point;
    private MissionStatus status;
    private Boolean isSuccess;
    private LocalDateTime acceptTime;
    private LocalDateTime successTime;

    public static MemberMissionDto from(MemberMission mm) {
        return MemberMissionDto.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .missionSpec(mm.getMission().getDescription())
                .point(mm.getMission().getPoint())
                .status(mm.getStatus())
                .isSuccess(mm.getIsSuccess())
                .acceptTime(mm.getAcceptTime())
                .successTime(mm.getSuccessTime())
                .build();
    }
}
