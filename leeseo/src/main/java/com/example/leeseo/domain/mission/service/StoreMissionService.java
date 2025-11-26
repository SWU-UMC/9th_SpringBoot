package com.example.leeseo.domain.mission.service;

import com.example.leeseo.domain.mission.converter.MissionConverter;
import com.example.leeseo.domain.mission.dto.MissionReqDTO;
import com.example.leeseo.domain.mission.dto.MissionResDTO;
import com.example.leeseo.domain.mission.entity.Mission;
import com.example.leeseo.domain.mission.exception.MissionException;
import com.example.leeseo.domain.mission.repository.MissionRepository;
import com.example.leeseo.domain.store.entity.Store;
import com.example.leeseo.domain.store.exception.code.StoreErrorCode;
import com.example.leeseo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.JoinDTO saveMission(
            Long store_id,
            MissionReqDTO.JoinDTO dto
    ){
        Store store = storeRepository.findById(store_id)
                .orElseThrow(() -> new MissionException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);

        return MissionConverter.toJoinDTO(mission);
    }
}
