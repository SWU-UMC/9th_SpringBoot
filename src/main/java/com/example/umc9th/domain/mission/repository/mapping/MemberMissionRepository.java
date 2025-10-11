package com.example.umc9th.domain.mission.repository.mapping;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.dto.MemberMissionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT new com.example.umc9th.domain.mission.dto.MemberMissionDto(s.name, m.point, m.created_at, m.conditional, mm.status)" +
            "FROM MemberMission mm JOIN mm.mission m  JOIN m.store s" +
            " WHERE mm.member.id = :memberId" +
            "  AND mm.status = :status" +
            " ORDER BY mm.id DESC")
    Page<MemberMissionDto> findUserMissionByStatus(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            Pageable pageable);
}
