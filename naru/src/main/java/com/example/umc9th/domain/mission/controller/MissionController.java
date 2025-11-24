package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.enums.MissionStatus;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.common.dto.SliceResponseDto;
import com.example.umc9th.global.entity.apiPayload.ApiResponse;
import com.example.umc9th.global.entity.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
@Validated
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

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션을 최신순으로 10개씩 조회합니다. (무한 스크롤)")
    @GetMapping("/store/{storeId}")
    public ApiResponse<SliceResponseDto<MissionResponseDto.MissionPreviewDto>> getMissionsByStore(
            @Parameter(description = "가게 ID", example = "1")
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)", example = "1")
            @CheckPage
            @RequestParam Integer page
    ) {
        SliceResponseDto<MissionResponseDto.MissionPreviewDto> result = missionService.getMissionsByStore(storeId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "내가 도전 중(ACTIVE)이거나 완료(COMPLETED)한 미션을 모아봅니다. (페이징 포함)")
    @GetMapping("/my")
    public ApiResponse<SliceResponseDto<MissionResponseDto.MyMissionDto>> getMyMissions(
            @Parameter(description = "로그인된 사용자 ID", example = "1")
            @RequestParam Long userId,
            @Parameter(description = "미션 상태 (ACTIVE: 진행중, COMPLETED: 완료)", example = "ACTIVE")
            @RequestParam(defaultValue = "ACTIVE") MissionStatus status,
            @Parameter(description = "페이지 번호 (1 이상)", example = "1")
            @CheckPage
            @RequestParam Integer page
    ) {
        SliceResponseDto<MissionResponseDto.MyMissionDto> result = missionService.getMyMissions(userId, status, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}