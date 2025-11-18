package com.umc.umc9th.domain.mission.converter;

import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import com.umc.umc9th.domain.mission.entity.Mission;
import com.umc.umc9th.domain.mission.entity.UserMission;
import com.umc.umc9th.domain.user.entity.User;
import java.time.LocalDateTime;

public class MissionConverter {

  public static UserMission toUserMission(User user, Mission mission) {
    return UserMission.builder()
        .user(user)
        .mission(mission)
        .status("PROGRESS")
        .startedAt(LocalDateTime.now())
        .build();
  }

  public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission um) {
    return MissionResDTO.ChallengeDTO.builder()
        .user_mission_id(um.getId())
        .status(um.getStatus())
        .started_at(um.getStartedAt().toString())
        .build();
  }
}
