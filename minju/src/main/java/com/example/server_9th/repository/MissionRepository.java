package com.example.server_9th.repository;

import com.example.server_9th.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    //현재 선택된 지역에서 아직 사용자가 도전하지 않은 미션 목록 조회
    @Query("""
            SELECT m
            FROM Mission m
            JOIN m.store s
            JOIN s.region a
            WHERE a.dong = :dong
              AND m.mission_id NOT IN (
                  SELECT mm.myMission_id.mission_id
                  FROM MyMission mm
                  WHERE mm.myMission_id.user_id = :userId
              )
            ORDER BY m.createdAt DESC
            """)
    Page<Mission> findAvailableMissionsByDongAndUserId(String dong, Long userId, Pageable pageable);
}
