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

    @PostMapping("/store/{store_id}/mission")
    public ApiResponse<MissionResDTO.JoinDTO> saveMission(
        @PathVariable Long store_id,
        @Valid @RequestBody MissionReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.POST_OK, storeMissionService.saveMission(store_id, dto));
    }

    @PostMapping("location/{location_id}/mission")
    public ApiResponse<MemberMissionResDTO.JoinDTO> saveMemberMission(
            @PathVariable Long location_id,
            @RequestParam Long mission_id,
            @RequestParam Long member_id
    ){
        return ApiResponse.onSuccess(MemberMissionSuccessCode.OK, memberMissionService.saveMemberMission(member_id,mission_id));
    }

    @GetMapping("/store/{store_id}/mission")
    public ApiResponse<MissionResDTO.StoreMissionListDTO> getStoreMissions(
            @PathVariable Long store_id,
            @PageValid Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.GET_OK, storeMissionService.getStoreMissions(store_id, page));
    }

    @GetMapping("/my-mission")
    public ApiResponse<MemberMissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam Long member_id,
            @RequestParam String status,
            @RequestParam Integer page
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.GET_OK, memberMissionService.getMyMissions(member_id, status, page));
    }

    @PatchMapping("/my-mission")
    public String updateMyMission(
            @RequestParam Long member_id,
            @RequestParam String status,
            @RequestParam Integer page
    ){

        return "redirect://my-mission";
    }

}