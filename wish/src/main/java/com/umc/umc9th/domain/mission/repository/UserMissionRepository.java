package com.umc.umc9th.domain.mission.repository;

import com.umc.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {

  // 진행중인 미션 조회
  List<UserMission> findAllByUser_IdAndStatusOrderByStartedAtDesc(Integer userId, String status);

  // 완료된 미션 조회
  List<UserMission> findAllByUser_IdAndStatusOrderByCompletedAtDesc(Integer userId, String status);

  // 완료한 미션 개수 조회
  @Query("""
        SELECT COUNT(um)
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        JOIN um.user u
        WHERE um.user.id = :userId
          AND um.status = 'COMPLETED'
          AND s.address.id = u.currentDong.id
    """)
  Long countCompletedMissionsInCurrentDong(@Param("userId") Integer userId);
}

