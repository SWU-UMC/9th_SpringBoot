package com.umc.umc9th.domain.mission.converter;

import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import com.umc.umc9th.domain.mission.entity.Mission;
import com.umc.umc9th.domain.mission.entity.UserMission;
import com.umc.umc9th.domain.user.entity.User;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

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

  public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
    return MissionResDTO.MissionPreviewDTO.builder()
        .missionId(mission.getId())
        .storeName(mission.getStore().getStoreName())
        .missionDescription(mission.getMissionDescription())
        .minAmount(mission.getMinAmount())
        .rewardPoints(mission.getRewardPoints())
        .deadline(mission.getDeadline())
        .isActive(mission.getIsActive())
        .build();
  }

  // 새로 추가: Page<Mission> -> MissionListDTO 변환
  public static MissionResDTO.MissionListDTO toMissionListDTO(Page<Mission> page) {
    return MissionResDTO.MissionListDTO.builder()
        .missions(page.getContent().stream()
            .map(MissionConverter::toMissionPreviewDTO)
            .toList())
        .listSize(page.getNumberOfElements())
        .totalPage(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .isFirst(page.isFirst())
        .isLast(page.isLast())
        .build();
  }
}
