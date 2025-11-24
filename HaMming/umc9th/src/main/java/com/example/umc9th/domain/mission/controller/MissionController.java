package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionDto;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.resolver.PageParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

    @Operation(
            summary = "특정 가게의 미션 목록 페이징 조회",
            description = "page는 1 이상의 값이어야 합니다."
    )
    @GetMapping("/store")
    public ApiResponse<List<MissionDto>> getMissionsByStore(
            @RequestParam Long storeId,
            @PageParam Integer page
    ) {
        List<MissionDto> result = missionQueryService
                .getMissionsByStore(storeId, page)
                .stream()
                .map(MissionDto::from)
                .toList();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
