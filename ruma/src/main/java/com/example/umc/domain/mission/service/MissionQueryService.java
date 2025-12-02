package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.dto.res.MissionPreviewDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public List<MissionPreviewDTO> getStoreMissionList(Long storeId) {

        List<Mission> missions = missionRepository.findByStore_StoreId(storeId);

        return missions.stream()
                .map(m -> MissionPreviewDTO.builder()
                        .missionId(m.getMissionId())
                        .description(m.getDescription())
                        .point(m.getPoint())
                        .deadline(m.getDeadline())
                        .build())
                .toList();
    }
}

