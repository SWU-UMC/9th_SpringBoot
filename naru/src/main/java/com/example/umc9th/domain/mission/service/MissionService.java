package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.enums.MissionStatus;
import com.example.umc9th.global.common.dto.SliceResponseDto;

public interface MissionService {

    MissionResponseDto.ChallengeResult challengeMission(MissionRequestDto.ChallengeMission request);

    SliceResponseDto<MissionResponseDto.MissionPreviewDto> getMissionsByStore(Long storeId, Integer page);

    SliceResponseDto<MissionResponseDto.MyMissionDto> getMyMissions(Long userId, MissionStatus status, Integer page);

    MissionResponseDto.MyMissionDto completeMission(Long userMissionId, Long userId);
}