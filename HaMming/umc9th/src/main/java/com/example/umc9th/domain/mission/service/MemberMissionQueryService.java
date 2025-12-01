package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MemberMission> getOngoingMissions(Long memberId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 10);
        return memberMissionRepository.findOngoingMissions(memberId, pageable);
    }
}
