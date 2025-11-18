package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.error.MissionErrorCode; // MissionErrorCode import
import com.example.umc9th.domain.mission.repository.MissionRepository; // (가정)
import com.example.umc9th.domain.mission.repository.UserMissionRepository; // (가정)
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.error.UserErrorCode; // UserErrorCode import
import com.example.umc9th.domain.user.repository.UserRepository; // (가정)
import com.example.umc9th.global.entity.apiPayload.exception.GeneralException; // GeneralException import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResponseDto.ChallengeResult challengeMission(MissionRequestDto.ChallengeMission request) {

        // User와 Mission 엔티티 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(UserErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        // 미션 중복 도전 확인
        if (userMissionRepository.existsByUserAndMission(user, mission)) {
            throw new GeneralException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }

        // 3. UserMission 엔티티로 변환 및 상태를 ACTIVE로 설정
        UserMission newUserMission = MissionConverter.toUserMission(user, mission);

        // 4. UserMission 저장
        UserMission savedUserMission = userMissionRepository.save(newUserMission);

        // 5. 결과 DTO로 변환하여 반환
        return MissionConverter.toChallengeResultDto(savedUserMission);
    }
}