package com.example.leeseo.domain.mission.converter;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.enums.MissionStatus;
import com.example.leeseo.domain.mission.dto.MemberMissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMissionResDTO.JoinDTO toJoinDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.JoinDTO.builder()
                .member_mission_id(memberMission.getId())
                .createdAt(memberMission.getCreated_at())
                .build();
    }

    public static MemberMission toMemberMission(
            Member member,
            Mission mission,
            MissionStatus status
    ){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(status)
                .build();
    }
}
