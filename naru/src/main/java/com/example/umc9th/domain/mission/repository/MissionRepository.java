package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 도전 가능한 미션 조회

    // 1) 메서드 이름 기반 쿼리
    @EntityGraph(attributePaths = {"store", "store.food"})
    Slice<Mission> findByStoreAndLocation(
            Long locationId,
            Long lastId,
            Pageable pageable
    );

    // 2) @Query 기반 쿼리
    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH s.food f
        WHERE s.location.id = :locationId
          AND m.id > :lastId
        ORDER BY m.id ASC
        """)
    Slice<Mission> findMissionsByLocation(
            @Param("locationId") Long locationId,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

}
