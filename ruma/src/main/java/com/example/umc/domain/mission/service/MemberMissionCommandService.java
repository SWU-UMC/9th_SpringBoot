package com.example.umc.domain.mission.service;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.mission.converter.MemberMissionConverter;
import com.example.umc.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.exception.MemberMissionException;
import com.example.umc.domain.mission.exception.MissionException;
import com.example.umc.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc.domain.mission.exception.code.MissionErrorCode;
import com.example.umc.domain.mission.mapping.MemberMission;
import com.example.umc.domain.mission.repository.MemberMissionRepository;
import com.example.umc.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    private static final Long FIXED_MEMBER_ID = 1L;

    @Transactional
    public MemberMissionResDTO.ChallengeDTO challenge(Long missionId) {

        // 1. 하드코딩된 유저
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2. 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 3. 이미 도전했는지 확인
        boolean exists = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (exists) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_CHALLENGED);
        }

        // 4. 엔티티 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        memberMissionRepository.save(memberMission);

        // 5. DTO 변환 후 반환
        return MemberMissionConverter.toChallengeDTO(memberMission);
    }
    // 내가 진행한 미션 목록
    @Transactional
    public List<MemberMissionResDTO.ChallengeDTO> getMyMissionList(){

        Member member=memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        List<MemberMission> memberMissions = memberMissionRepository.findByMember(member);

        return memberMissions.stream()
                .map(MemberMissionConverter::toChallengeDTO)
                .toList();

    }
}

