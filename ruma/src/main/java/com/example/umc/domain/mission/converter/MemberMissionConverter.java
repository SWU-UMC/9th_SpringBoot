package com.example.umc.domain.mission.converter;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.mapping.MemberMission;
import com.example.umc.domain.mission.enums.MissionStatus;

public class MemberMissionConverter {

    // Entity → DTO
    public static MemberMissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {

        Mission mission = memberMission.getMission();
        var store = mission.getStore();   // Mission → Store 접근

        return MemberMissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .missionId(mission.getMissionId())
                .storeId(store.getStoreId())
                .storeName(store.getName())
                .status(memberMission.getStatus().name())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .completedAt(memberMission.getCompletedAt())
                .build();
    }

    // DTO → Entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();
    }
}

