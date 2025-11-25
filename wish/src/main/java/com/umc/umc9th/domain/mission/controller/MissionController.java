package com.umc.umc9th.domain.mission.controller;

import com.umc.umc9th.domain.mission.dto.MissionReqDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO.ChallengeDTO;
import com.umc.umc9th.domain.mission.exception.MissionSuccessCode;
import com.umc.umc9th.domain.mission.service.MissionService;
import com.umc.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

  private final MissionService missionService;

  @PostMapping("/challenge")
  public ApiResponse<ChallengeDTO> challengeMission(
      @RequestBody MissionReqDTO.ChallengeDTO dto
  ) {
    Integer userId = 1;

    return ApiResponse.onSuccess(
        MissionSuccessCode.CHALLENGED,
        missionService.challengeMission(userId, dto)
    );
  }
}
