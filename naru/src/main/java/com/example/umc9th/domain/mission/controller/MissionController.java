package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.entity.apiPayload.ApiResponse;
import com.example.umc9th.global.entity.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(
            summary = "미션 도전하기 API",
            description = "사용자가 특정 미션에 도전합니다. UserMission 테이블에 'ACTIVE' 상태로 등록됩니다."
    )
    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDto.ChallengeResult> challengeMission(
            @Valid @RequestBody MissionRequestDto.ChallengeMission request
    ) {
        MissionResponseDto.ChallengeResult result = missionService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}