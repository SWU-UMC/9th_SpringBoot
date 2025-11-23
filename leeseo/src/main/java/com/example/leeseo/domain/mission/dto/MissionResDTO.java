package com.example.leeseo.domain.mission.dto;

import com.example.leeseo.domain.mission.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    @Schema(name = "MissionResponse")
    public record JoinDTO(
        Long id,
        LocalDateTime createdAt
    ){
    }

    @Builder
    public record StoreMissionListDTO(
        List<StoreMissionDTO> missionList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ){
    }

    @Builder
    public record StoreMissionDTO(
        LocalDate deadline,
        String conditional,
        Integer point,
        LocalDateTime createdAt
    ){
    }

}
