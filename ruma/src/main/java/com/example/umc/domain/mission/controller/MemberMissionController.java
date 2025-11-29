package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc.domain.mission.service.MemberMissionCommandService;
import com.example.umc.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions/my")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    // 1) 미션 도전하기 (생성)
    @PostMapping("/{missionId}")
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challenge(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CHALLENGED,
                memberMissionCommandService.challenge(missionId)
        );
    }

    // 2) 내가 진행중인 미션 목록 조회
    @GetMapping
    public ApiResponse<List<MemberMissionResDTO.ChallengeDTO>> getMyMissions() {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.MY_MISSION_LIST_SUCCESS,
                memberMissionCommandService.getMyMissionList()
        );
    }
}
