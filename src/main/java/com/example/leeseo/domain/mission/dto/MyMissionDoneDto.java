package com.example.leeseo.domain.mission.dto;

import lombok.Getter;

@Getter
public class MyMissionDoneDto {
    private Long doneMissionCnt;
    private Long allMissionCnt;

    public MyMissionDoneDto(Long doneMissionCnt, Long allMissionCnt) {
        this.doneMissionCnt = doneMissionCnt;
        this.allMissionCnt = allMissionCnt;
    }
}
