package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.MissionChallengeRequestDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberMission challengeMission(MissionChallengeRequestDto req) {

        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(req.getMissionId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        MemberMission mm = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.OnGoing)
                .isSuccess(false)
                .acceptTime(LocalDateTime.now())
                .build();

        return memberMissionRepository.save(mm);
    }
}
