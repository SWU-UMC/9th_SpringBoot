package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMission finishMission(Long memberMissionId);
}
