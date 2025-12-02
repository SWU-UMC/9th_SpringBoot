package com.example.leeseo.domain.mission.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberMissionHomeDto {
    private String name;
    private String conditional;
    private Integer point;
    private LocalDateTime createdAt;

    public MemberMissionHomeDto(String name, String conditional, Integer point, LocalDateTime createdAt) {
        this.name = name;
        this.conditional = conditional;
        this.point = point;
        this.createdAt = createdAt;
    }
}
