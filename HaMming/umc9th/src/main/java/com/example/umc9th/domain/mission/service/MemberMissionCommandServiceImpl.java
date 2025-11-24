package com.example.umc9th.domain.mission.service;


import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission finishMission(Long memberMissionId) {

        MemberMission mm = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 이미 완료된 미션이면 막을 수도 있지만 지금은 단순 변경 처리
        mm.setStatus(MissionStatus.Finish);
        mm.setIsSuccess(true);
        mm.setSuccessTime(LocalDateTime.now());

        return memberMissionRepository.save(mm);
    }
}
