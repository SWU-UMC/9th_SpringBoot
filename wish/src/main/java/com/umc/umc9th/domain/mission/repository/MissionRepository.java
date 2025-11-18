package com.umc.umc9th.domain.mission.repository;

import com.umc.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

  // 도전 가능한 미션 목록 조회
  @Query(value = """
        SELECT 
            m.mission_id,
            s.store_name,
            m.mission_description,
            m.min_amount,
            m.reward_points,
            m.deadline,
            DATEDIFF(m.deadline, NOW()) AS days_left
        FROM mission m
        JOIN store s ON m.store_id = s.store_id
        JOIN user u ON u.current_dong_id = s.address_id
        LEFT JOIN user_mission um 
            ON m.mission_id = um.mission_id 
            AND um.user_id = :userId
        WHERE u.user_id = :userId
          AND m.is_active = true
          AND m.deadline > NOW()
          AND um.user_mission_id IS NULL
        ORDER BY m.deadline ASC
    """, nativeQuery = true)
  List<Object[]> findAvailableMissionsInCurrentDong(@Param("userId") Integer userId);
}
