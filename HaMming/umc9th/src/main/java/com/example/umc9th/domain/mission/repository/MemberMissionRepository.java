package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    @Query("""
        SELECT mm
        FROM MemberMission mm
        JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.OnGoing
        ORDER BY mm.id DESC
        """)
    List<MemberMission> findOngoingMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
