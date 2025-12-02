package com.example.leeseo.domain.mission.dto;

import com.example.leeseo.domain.mission.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    @Schema(name = "MemberMissionResponse")
    public record JoinDTO(
        Long memberMissionId,
        LocalDateTime createdAt
    ){
    }

    @Builder
    public record PatchMissionDTO(
            Long memberMissionId,
            MissionStatus status,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record MyMissionListDTO(
            List<MyMissionDTO> myMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){
    }

    @Builder
    public record MyMissionDTO(
            long memberMissionId,
            MissionStatus status,
            LocalDate deadline,
            String conditional,
            Integer point,
            LocalDateTime createdAt
    ){
    }
}
