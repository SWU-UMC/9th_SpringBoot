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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreMissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionResDTO.JoinDTO saveMission(
            Long storeId,
            MissionReqDTO.JoinDTO dto
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);

        return MissionConverter.toJoinDTO(mission);
    }

    public MissionResDTO.StoreMissionListDTO getStoreMissions(
            Long storeId,
            Integer page
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(StoreErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page -1 , 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);
        return MissionConverter.toStoreMissionList(result);
    }
}
