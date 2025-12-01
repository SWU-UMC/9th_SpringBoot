package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MemberMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class MemberMissionConverter {

    public MemberMissionDto toMemberMissionDto(MemberMission mm) {
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
