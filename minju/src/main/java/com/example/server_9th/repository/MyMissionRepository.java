package com.example.server_9th.repository;

import com.example.server_9th.domain.enums.MissionStatus;
import com.example.server_9th.domain.mapping.myMission.MyMission;
import com.example.server_9th.domain.mapping.myMission.MyMissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMissionRepository extends JpaRepository<MyMission, MyMissionId> {
    //특정 회원의 미션 상태에 따른 미션 목록 조회
    @Query("""
            SELECT mm
            FROM MyMission mm
            WHERE mm.myMission_id.user_id = :userId
              AND mm.missionStatus = :status
            ORDER BY mm.updatedAt DESC
            """)
    Page<MyMission> findAllByUserIdAndStatus(Long userId, MissionStatus status, Pageable pageable);

    //특정 회원의 전체 미션 목록 조회
    @Query("""
            SELECT mm
            FROM MyMission mm
            WHERE mm.myMission_id.user_id = :userId
            ORDER BY
                CASE
                    WHEN mm.missionStatus = 'IN_PROGRESS' THEN 1
                    WHEN mm.missionStatus = 'COMPLETE' THEN 2
                    ELSE 3
                END,
                mm.updatedAt DESC
            """)
    Page<MyMission> findAllByUserId(Long userId, Pageable pageable);
}
