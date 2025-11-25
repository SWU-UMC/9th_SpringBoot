package com.umc.umc9th.domain.mission.service;

import com.umc.umc9th.domain.mission.converter.MissionConverter;
import com.umc.umc9th.domain.mission.dto.MissionReqDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import com.umc.umc9th.domain.mission.entity.Mission;
import com.umc.umc9th.domain.mission.entity.UserMission;
import com.umc.umc9th.domain.mission.exception.MissionErrorCode;
import com.umc.umc9th.domain.mission.exception.MissionException;
import com.umc.umc9th.domain.mission.repository.MissionRepository;
import com.umc.umc9th.domain.mission.repository.UserMissionRepository;
import com.umc.umc9th.domain.user.entity.User;
import com.umc.umc9th.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

  private final MissionRepository missionRepository;
  private final UserMissionRepository userMissionRepository;
  private final UserRepository userRepository;

  @Override
  public MissionResDTO.ChallengeDTO challengeMission(Integer userId, MissionReqDTO.ChallengeDTO dto) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

    Mission mission = missionRepository.findById(dto.mission_id())
        .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

    if (!mission.getIsActive()) {
      throw new MissionException(MissionErrorCode.MISSION_INACTIVE);
    }

    if (mission.getDeadline().isBefore(LocalDateTime.now())) {
      throw new MissionException(MissionErrorCode.MISSION_EXPIRED);
    }

    userMissionRepository.findByUserIdAndMissionId(userId, dto.mission_id())
        .ifPresent(m -> {
          throw new MissionException(MissionErrorCode.ALREADY_IN_PROGRESS);
        });

    UserMission userMission = MissionConverter.toUserMission(user, mission);
    userMissionRepository.save(userMission);

    return MissionConverter.toChallengeDTO(userMission);
  }
}
