package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH m.region r
        WHERE r.id = :regionId
          AND (:cursor IS NULL OR m.id < :cursor)
          AND m.deadline > CURRENT_TIMESTAMP
          AND NOT EXISTS (
              SELECT mm FROM MemberMission mm
              WHERE mm.mission.id = m.id
              AND mm.member.id = :memberId
          )
        ORDER BY m.id DESC
        """)
    List<Mission> findAvailableMissionsByRegionWithCursor(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
    List<Mission> findByRegionIdAndIdLessThanOrderByIdDesc(Long regionId, Long cursor, Pageable pageable);

    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        WHERE s.id = :storeId
        ORDER BY m.id DESC
        """)
    List<Mission> findByStoreIdPaged(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}
