package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;

public interface MissionService {

    MissionResponseDto.ChallengeResult challengeMission(MissionRequestDto.ChallengeMission request);
}