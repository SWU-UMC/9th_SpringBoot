package com.example.leeseo.domain.mission.converter;

import com.example.leeseo.domain.mission.dto.MissionReqDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResDTO.JoinDTO toJoinDTO(
            Mission mission
    ){
        return MissionResDTO.JoinDTO.builder()
                .id(mission.getId())
                .createdAt(mission.getCreatedAt())
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
    
    public static MissionResDTO.StoreMissionListDTO toStoreMissionList(
            Page<Mission> result
    ){
        return MissionResDTO.StoreMissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toStoreMission)
                        .toList())
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
    
    public static MissionResDTO.StoreMissionDTO toStoreMission(
            Mission mission      
    ){
        return MissionResDTO.StoreMissionDTO.builder()
                .deadline(mission.getDeadline())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
