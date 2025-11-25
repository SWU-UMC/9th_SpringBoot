package com.umc.umc9th.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class MissionResDTO {

  @Builder
  public record ChallengeDTO(
      Integer user_mission_id,
      String status,
      String started_at
  ){}

  @Builder
  public record MissionListDTO(
      List<MissionPreviewDTO> missions,
      Integer listSize,
      Integer totalPage,
      Long totalElements,
      Boolean isFirst,
      Boolean isLast
  ){}

  @Builder
  public record MissionPreviewDTO(
      Integer missionId,
      String storeName,
      String missionDescription,
      Integer minAmount,
      Integer rewardPoints,
      LocalDateTime deadline,
      Boolean isActive
  ){}
}