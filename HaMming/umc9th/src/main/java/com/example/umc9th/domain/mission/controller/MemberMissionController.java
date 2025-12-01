package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MemberMissionDto;
import com.example.umc9th.domain.mission.service.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.resolver.PageParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member-missions")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(summary = "내 진행 중 미션 목록 조회(페이징)")
    @GetMapping("/ongoing")
    public ApiResponse<List<MemberMissionDto>> getOngoingMissions(
            @RequestParam Long memberId,
            @PageParam Integer page
    ) {
        List<MemberMissionDto> result = memberMissionQueryService.getOngoingMissions(memberId, page)
                .stream()
                .map(MemberMissionDto::from)
                .toList();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @Operation(summary = "미션 완료 처리 후 변경된 상태 반환")
    @PostMapping("/finish")
    public ApiResponse<MemberMissionDto> finishMission(
            @RequestParam Long memberMissionId
    ) {
        MemberMissionDto result =
                MemberMissionDto.from(memberMissionCommandService.finishMission(memberMissionId));

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
