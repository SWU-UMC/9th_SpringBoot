package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
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

    public static MemberMissionDto from(MemberMission mm) {
        Mission mission = mm.getMission();
        Store store = mission.getStore();

        return MemberMissionDto.builder()
                .memberMissionId(mm.getId())
                .missionId(mission.getId())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .status(mm.getStatus())
                .isSuccess(mm.getIsSuccess())
                .acceptTime(mm.getAcceptTime())
                .successTime(mm.getSuccessTime())
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .build();
    }
}
