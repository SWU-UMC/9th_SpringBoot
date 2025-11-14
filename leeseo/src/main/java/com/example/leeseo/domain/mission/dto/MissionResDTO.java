package com.example.leeseo.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    @Schema(name = "MissionResponse")
    public record JoinDTO(
        Long id,
        LocalDateTime createdAt
    ){
    }
}
