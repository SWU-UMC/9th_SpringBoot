package com.umc.umc9th.domain.mission.service;

import com.umc.umc9th.domain.mission.dto.MissionReqDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Pageable;

public interface MissionService {
  MissionResDTO.ChallengeDTO challengeMission(Integer userId, MissionReqDTO.ChallengeDTO dto);

  MissionResDTO.MissionListDTO getStoreMissions(Integer storeId, Pageable pageable);

}
