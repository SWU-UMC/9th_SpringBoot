package com.example.leeseo.domain.mission.service;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.mission.converter.MissionConverter;
import com.example.leeseo.domain.mission.enums.MissionStatus;
import com.example.leeseo.domain.member.exception.code.MemberErrorCode;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.mission.converter.MemberMissionConverter;
import com.example.leeseo.domain.mission.dto.MemberMissionResDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.mission.entity.mapping.MemberMission;
import com.example.leeseo.domain.mission.exception.MemberMissionException;
import com.example.leeseo.domain.mission.exception.MissionException;
import com.example.leeseo.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.leeseo.domain.mission.exception.code.MissionErrorCode;
import com.example.leeseo.domain.mission.repository.MissionRepository;
import com.example.leeseo.domain.mission.repository.mapping.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = getMember(member_id);

        Mission mission = missionRepository.findById(mission_id)
                .orElseThrow(()-> new MissionException(MissionErrorCode.NOT_FOUND));

        if (memberMissionRepository.existsByMemberIdAndMissionId(member_id, mission_id)){
            throw new MissionException(MemberMissionErrorCode.ALREADY_JOINED);
        }

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission, MissionStatus.IN_PROGRESS);
        memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toJoinDTO(memberMission);
    }

    public MemberMissionResDTO.MyMissionListDTO getMyMissions(
            Long member_id,
            String status,
            Integer page
    ){
        MissionStatus missionStatus = MissionStatus.from(status);
        Member member = getMember(member_id);
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<MemberMission> result = memberMissionRepository.findMemberMissionByMemberAndStatus(member,missionStatus, pageRequest);
        return MemberMissionConverter.toMyMemberList(result);
    }

    @Transactional
    public MemberMissionResDTO.PatchMissionDTO updateMyMissions(
            Long memberMission_id,
            String status
    ){
        MissionStatus missionStatus = MissionStatus.from(status);
        MemberMission memberMission = memberMissionRepository
                .findById(memberMission_id)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.NOT_FOUND));

        memberMission.updateStatus(missionStatus);

        if (!memberMission.getStatus().equals(missionStatus)){
            throw new MemberMissionException(MemberMissionErrorCode.NOT_MODIFIED);
        }
        return MemberMissionConverter.toPatchDTO(memberMission);
    }

    private Member getMember(Long member_id) {
        return memberRepository.findById(member_id)
                .orElseThrow(() -> new MissionException(MemberErrorCode.NOT_FOUND));
    }
}
