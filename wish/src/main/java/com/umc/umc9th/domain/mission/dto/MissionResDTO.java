package com.umc.umc9th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

  @Builder
  public record ChallengeDTO(
      Integer user_mission_id,
      String status,
      String started_at
  ){}
}