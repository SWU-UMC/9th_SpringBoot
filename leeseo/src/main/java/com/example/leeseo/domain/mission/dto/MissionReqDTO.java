package com.example.leeseo.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    @Schema(name = "MissionRequest")
    public record JoinDTO(
        @NotNull
        LocalDate deadLine,
        @NotBlank
        String conditional,
        @NotNull
        Integer point
    ){
    }
}
