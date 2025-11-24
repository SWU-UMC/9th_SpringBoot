package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberMissionDto {

    private Long memberMissionId;
    private Long missionId;
    private String description;

    private Integer point;
    private MissionStatus status;
    private Boolean isSuccess;
    private LocalDateTime acceptTime;
    private LocalDateTime successTime;

    private Long storeId;
    private String storeName;
}
