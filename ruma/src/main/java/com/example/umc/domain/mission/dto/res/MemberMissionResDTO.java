package com.example.umc.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long memberMissionId,
            Long missionId,
            Long storeId,
            String storeName,
            String status,           // enum 처리
            String description,
            Integer point,
            LocalDateTime deadline,
            LocalDateTime completedAt
    ){}
}

