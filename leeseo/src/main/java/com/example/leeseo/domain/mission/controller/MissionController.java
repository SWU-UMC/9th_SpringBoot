package com.example.leeseo.domain.mission.controller;

import com.example.leeseo.domain.mission.dto.MemberMissionResDTO;
import com.example.leeseo.domain.mission.dto.MissionReqDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.leeseo.domain.mission.exception.code.MissionSuccessCode;
import com.example.leeseo.domain.mission.service.MemberMissionService;
import com.example.leeseo.domain.mission.service.StoreMissionService;
import com.example.leeseo.global.annotation.PageValid;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs{

    private final StoreMissionService storeMissionService;
    private final MemberMissionService memberMissionService;

    @PostMapping("/store/{storeId}/mission")
    public ApiResponse<MissionResDTO.JoinDTO> saveMission(
        @PathVariable Long storeId,
        @Valid @RequestBody MissionReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.POST_OK, storeMissionService.saveMission(storeId, dto));
    }

    @PostMapping("location/{locationId}/mission")
    public ApiResponse<MemberMissionResDTO.JoinDTO> saveMemberMission(
            @PathVariable Long locationId,
            @RequestParam Long missionId,
            @RequestParam Long memberId
    ){
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionService.saveMemberMission(memberId,missionId));
    }

    @GetMapping("/store/{storeId}/mission")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @PageValid Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.GET_OK, storeMissionService.getStoreMissions(storeId, page));
    }

    @GetMapping("/my-mission")
    public ApiResponse<MemberMissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam String status,
            @RequestParam Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.GET_OK, memberMissionService.getMyMissions(memberId, status, page));
    }

    @PatchMapping("/my-mission")
    public ApiResponse<MemberMissionResDTO.PatchMissionDTO> updateMyMission(
            @RequestParam Long memberMissionId,
            @RequestParam String status
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.PATCH_OK, memberMissionService.updateMyMissions(memberMissionId, status));
    }
}