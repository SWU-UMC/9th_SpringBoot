package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    /**
     *진행 중 미션 목록 조회 (MissionStatus = OnGoing)
     */
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.OnGoing
          AND (:cursor IS NULL OR mm.id < :cursor)
        ORDER BY mm.id DESC
        """)
    List<MemberMission> findOngoingMissionsWithCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );


    /**
     * 진행 완료 미션 목록 조회 (MissionStatus = Finish)
     */
    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.Finish
          AND (:cursor IS NULL OR mm.id < :cursor)
        ORDER BY mm.id DESC
        """)
    List<MemberMission> findCompletedMissionsWithCursor(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
