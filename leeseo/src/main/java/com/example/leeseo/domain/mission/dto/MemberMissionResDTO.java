package com.example.leeseo.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    @Schema(name = "MemberMissionResponse")
    public record JoinDTO(
        Long member_mission_id,
        LocalDateTime createdAt
    ){
    }
}
