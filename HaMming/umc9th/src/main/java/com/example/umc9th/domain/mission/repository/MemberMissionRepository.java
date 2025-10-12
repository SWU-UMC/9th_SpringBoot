package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    /**
     *진행 중 미션 조회 (isSuccess = false)
     */
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.isSuccess = false
          AND (:cursor IS NULL OR mm.id < :cursor)
        ORDER BY mm.id DESC
        """)
    List<MemberMission> findOngoingMissionsWithCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );


    /**
     * 진행 완료 미션 조회 (isSuccess = true)
     */
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.isSuccess = true
          AND (:cursor IS NULL OR mm.id < :cursor)
        ORDER BY mm.id DESC
        """)
    List<MemberMission> findCompletedMissionsWithCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
