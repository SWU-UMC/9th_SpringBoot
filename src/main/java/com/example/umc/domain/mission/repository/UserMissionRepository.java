package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.mapping.UserMission;
import com.example.umc.domain.mission.enums.MissionStatus;  // MissionStatus enum 임포트
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 유저의 미션 목록 조회
    List<UserMission> findByUser_UserIdAndStatusOrderByUserMissionIdDesc(Long userId, MissionStatus status, Pageable pageable);
}
