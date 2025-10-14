package com.example.leeseo.domain.mission.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberMissionHomeDto {
    private String name;
    private String conditional;
    private Integer point;
    private LocalDateTime created_at;

    public MemberMissionHomeDto(String name, String conditional, Integer point, LocalDateTime created_at) {
        this.name = name;
        this.conditional = conditional;
        this.point = point;
        this.created_at = created_at;
    }
}
