package com.example.leeseo.domain.mission.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberMissionDto {
    private String name;
    private Integer point;
    private LocalDateTime created_at;
    private String conditional;
    private String status;

    public MemberMissionDto(String name, Integer point, LocalDateTime created_at, String conditional, String status) {
        this.name = name;
        this.point = point;
        this.created_at = created_at;
        this.conditional = conditional;
        this.status = status;
    }
}
