package com.example.leeseo.domain.mission.controller;

import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MissionControllerDocs {
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API by 이서",
            description = "특정 가게의 미션 목록을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(Long store_id, Integer page);
}
