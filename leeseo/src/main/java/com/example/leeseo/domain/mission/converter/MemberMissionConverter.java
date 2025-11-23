package com.example.leeseo.domain.mission.converter;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.enums.MissionStatus;
import com.example.leeseo.domain.mission.dto.MemberMissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

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

    public static MemberMissionResDTO.MyMissionListDTO toMyMemberList(
            Page<MemberMission> result
    ){
        return MemberMissionResDTO.MyMissionListDTO.builder()
                .myMissionList(result.getContent().stream()
                        .map(MemberMissionConverter::toMyMember)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.MyMissionDTO toMyMember(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus())
                .deadline(memberMission.getMission().getDeadline())
                .conditional(memberMission.getMission().getConditional())
                .point(memberMission.getMission().getPoint())
                .createdAt(memberMission.getMember().getCreated_at())
                .build();
    }
}
