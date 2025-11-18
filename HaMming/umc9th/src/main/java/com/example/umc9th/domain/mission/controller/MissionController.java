package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionChallengeRequestDto;
import com.example.umc9th.domain.mission.dto.MissionChallengeResponse;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MissionChallengeResponse> challengeMission(
            @RequestBody MissionChallengeRequestDto req
    ) {
        MemberMission mm = missionCommandService.challengeMission(req);

        MissionChallengeResponse result = MissionChallengeResponse.builder()
                .memberMissionId(mm.getId())
                .build();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                result
        );
    }
}
