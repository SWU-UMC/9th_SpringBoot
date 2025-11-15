package com.example.leeseo.domain.mission.service;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.enums.MissionStatus;
import com.example.leeseo.domain.member.exception.code.MemberErrorCode;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.mission.converter.MemberMissionConverter;
import com.example.leeseo.domain.mission.dto.MemberMissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.mission.entity.mapping.MemberMission;
import com.example.leeseo.domain.mission.exception.MissionException;
import com.example.leeseo.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.leeseo.domain.mission.exception.code.MissionErrorCode;
import com.example.leeseo.domain.mission.repository.MissionRepository;
import com.example.leeseo.domain.mission.repository.mapping.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberMissionResDTO.JoinDTO saveMemberMission(
            Long member_id,
            Long mission_id
    ){
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new MissionException(MemberErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(mission_id)
                .orElseThrow(()-> new MissionException(MissionErrorCode.NOT_FOUND));

        if (memberMissionRepository.existsByMemberIdAndMissionId(member_id, mission_id)){
            throw new MissionException(MemberMissionErrorCode.ALREADY_JOINED);
        }

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission, MissionStatus.IN_PROGRESS);
        memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toJoinDTO(memberMission);
    }
}
