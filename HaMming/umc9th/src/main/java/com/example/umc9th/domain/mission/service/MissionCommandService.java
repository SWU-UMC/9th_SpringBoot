package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionChallengeRequestDto;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public interface MissionCommandService {
    MemberMission challengeMission(MissionChallengeRequestDto req);
}
