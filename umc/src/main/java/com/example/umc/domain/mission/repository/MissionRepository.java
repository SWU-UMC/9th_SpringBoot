package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;  // 올바른 Pageable 임포트
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 미션 목록 조회
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region.regionId = :regionId " +
            "AND NOT EXISTS (SELECT 1 FROM UserMission um WHERE um.user.userId = :userId AND um.mission.missionId = m.missionId) " +
            "ORDER BY m.createdAt DESC")
    List<Mission> findAvailableMissions(@Param("userId") Long userId, @Param("regionId") Long regionId, Pageable pageable);
}