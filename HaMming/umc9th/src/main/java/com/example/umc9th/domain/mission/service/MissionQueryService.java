package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;

    public List<Mission> getMissionsByStore(Long storeId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 10);
        return missionRepository.findByStoreIdPaged(storeId, pageable);
    }
}
