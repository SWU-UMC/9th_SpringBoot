package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc.domain.mission.service.MemberMissionCommandService;
import com.example.umc.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challenge(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MemberMissionSuccessCode.CHALLENGED,
                memberMissionCommandService.challenge(missionId)
        );
    }
}

