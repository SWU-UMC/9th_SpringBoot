package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionConverter {

    /**
     * User와 Mission 엔티티를 UserMission 엔티티로 변환
     */
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .build();
    }

    /**
     * UserMission 엔티티 → MissionResponseDto.ChallengeResult DTO 변환
     */
    public static MissionResponseDto.ChallengeResult toChallengeResultDto(UserMission userMission) {
        return MissionResponseDto.ChallengeResult.builder()
                .userMissionId(userMission.getId())
                .build();
    }
}