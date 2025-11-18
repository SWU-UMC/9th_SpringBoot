package com.example.umc.domain.mission.converter;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.mapping.MemberMission;
import com.example.umc.domain.mission.enums.MissionStatus;

public class MemberMissionConverter {

    // Entity → DTO
    public static MemberMissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .missionId(memberMission.getMission().getMissionId())
                .memberId(memberMission.getMember().getMemberId())
                .status(memberMission.getStatus().name())
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
