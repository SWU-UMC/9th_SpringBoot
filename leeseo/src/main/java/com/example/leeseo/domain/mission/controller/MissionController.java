package com.example.leeseo.domain.mission.controller;

import com.example.leeseo.domain.mission.dto.MissionReqDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.exception.code.MissionSuccessCode;
import com.example.leeseo.domain.mission.service.StoreMissionService;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final StoreMissionService storeMissionService;

    @PostMapping("/store/{store_id}/mission")
    public ApiResponse<MissionResDTO.JoinDTO> saveMission(
        @PathVariable Long store_id,
        @Valid @RequestBody MissionReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.OK, storeMissionService.saveMission(store_id, dto));
    }
}
