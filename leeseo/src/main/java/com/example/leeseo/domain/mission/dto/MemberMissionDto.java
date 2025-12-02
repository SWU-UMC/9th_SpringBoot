package com.example.leeseo.domain.mission.dto;

import com.example.leeseo.domain.mission.enums.MissionStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberMissionDto {
    private String name;
    private Integer point;
    private LocalDateTime createdAt;
    private String conditional;
    private MissionStatus status;

    public MemberMissionDto(String name, Integer point, LocalDateTime createdAt, String conditional, MissionStatus status) {
        this.name = name;
        this.point = point;
        this.createdAt = createdAt;
        this.conditional = conditional;
        this.status = status;
    }
}
