package com.example.umc9th.domain.mission.entity.mapping;

import com.example.umc9th.domain.mission.entity.enums.MissionStatus;
import com.example.umc9th.global.common.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long missionId;

    private MissionStatus status;

}
