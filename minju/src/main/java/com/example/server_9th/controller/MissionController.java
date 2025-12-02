package com.example.server_9th.controller;

import com.example.server_9th.apiPayload.ApiResponse;
import com.example.server_9th.apiPayload.valid.ValidPage;
import com.example.server_9th.domain.Member;
import com.example.server_9th.dto.MissionDto;
import com.example.server_9th.service.mission.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.server_9th.apiPayload.code.SuccessCode._OK;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Validated
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/in-progress")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "로그인한 사용자가 진행중인 미션 목록을 page 단위(10개)로 조회합니다.")
    public ApiResponse<MissionDto.MissionPreviewListDto> getInProgressMissions(
            @Parameter(description = "1 이상의 페이지 번호", example = "1") @ValidPage
            @RequestParam Integer page, @AuthenticationPrincipal Member loginMember
    ) {

        MissionDto.MissionPreviewListDto dto =
                missionService.getMyInProgressMissions(loginMember, page);

        return ApiResponse.onSuccess(_OK,dto);
    }
/*
    @PatchMapping("/{missionId}/complete")
    @Operation(summary = "진행중인 미션 완료 처리", description = "미션을 IN_PROGRESS → COMPLETED 로 변경하고, 변경된 미션 목록을 반환합니다.")
    public ApiResponse<MissionDto.MissionPreviewListDto> completeMission(
            @Parameter(description = "완료 처리할 미션 ID", example = "10")
            @PathVariable Long missionId,
            @Parameter(description = "조회할 페이지 번호 (1 이상)", example = "1") @ValidPage
            @RequestParam Integer page, @AuthenticationPrincipal Member loginMember
    ) {

        missionService.completeMission(loginMember, missionId);

        MissionDto.MissionPreviewListDto dto = missionService.getMyMissions(loginMember, page);

        return ApiResponse.onSuccess(_OK,dto);
    }

 */


}
