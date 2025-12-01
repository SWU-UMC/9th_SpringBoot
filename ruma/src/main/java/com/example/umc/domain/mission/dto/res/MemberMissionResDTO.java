package com.example.umc.domain.mission.dto.res;

import lombok.Builder;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long memberMissionId,
            Long missionId,
            Long memberId,
            String status
    ){}
}

