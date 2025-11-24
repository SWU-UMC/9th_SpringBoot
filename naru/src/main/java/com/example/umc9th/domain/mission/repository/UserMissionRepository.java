package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.enums.MissionStatus;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 진행중/진행완료 미션 목록 조회

    // 1) 메서드 이름 기반 쿼리
    @EntityGraph(attributePaths = {"mission", "mission.store"}) // 조인 로딩을 EntityGraph로 처리
    Slice<UserMission> findByUserIdAndStatus(
            Long userId,
            MissionStatus status,
            Long lastId,
            Pageable pageable
    );

    // 2) @Query 기반 JPQL
    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        WHERE um.user.id = :userId
          AND um.status = :status
          AND (:lastId IS NULL OR um.id < :lastId)
        ORDER BY um.id DESC
        """)
    Slice<UserMission> findMissionsByStatus(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

    /**
     * 특정 사용자가 특정 미션에 도전 중인지 확인하는 쿼리 메서드
     */
    boolean existsByUserAndMission(User user, Mission mission);

    /**
     * 특정 사용자의 미션 목록 조회 (상태별, 페이징 포함)
     */
    @EntityGraph(attributePaths = {"mission", "mission.store"})
    Slice<UserMission> findAllByUserIdAndStatus(Long userId, MissionStatus status, Pageable pageable);
}
