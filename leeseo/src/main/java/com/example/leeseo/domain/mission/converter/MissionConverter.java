package com.example.leeseo.domain.mission.converter;

import com.example.leeseo.domain.mission.dto.MissionReqDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.store.entity.Store;

public class MissionConverter {

    public static MissionResDTO.JoinDTO toJoinDTO(
            Mission mission
    ){
        return MissionResDTO.JoinDTO.builder()
                .id(mission.getId())
                .createdAt(mission.getCreated_at())
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.JoinDTO dto
    ){
        return Mission.builder()
                .deadline(dto.deadLine())
                .conditional(dto.conditional())
                .point(dto.point())
                .store(store)
                .build();
    }
}
