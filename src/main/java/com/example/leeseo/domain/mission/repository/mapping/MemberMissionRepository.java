package com.example.leeseo.domain.mission.repository.mapping;

import com.example.leeseo.domain.mission.dto.MemberMissionHomeDto;
import com.example.leeseo.domain.mission.dto.MyMissionDoneDto;
import com.example.leeseo.domain.mission.entity.mapping.MemberMission;
import com.example.leeseo.domain.mission.dto.MemberMissionDto;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT new com.example.leeseo.domain.mission.dto.MemberMissionDto(s.name, m.point, m.created_at, m.conditional, mm.status)" +
            "FROM MemberMission mm JOIN mm.mission m  JOIN m.store s" +
            " WHERE mm.member.id = :memberId" +
            "  AND mm.status = :status" +
            " AND mm.id < :lastMissionId ORDER BY mm.id DESC")
    Slice<MemberMissionDto> findMemberMissionByStatus(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            @Param("lastMissionId") Long lastMissionId,
            Pageable pageable);

    @Query("SELECT new com.example.leeseo.domain.mission.dto" +
            ".MemberMissionHomeDto(s.name, m.conditional, m.point, m.created_at)" +
            " FROM MemberMission mm" +
            " JOIN mm.mission m" +
            " JOIN m.store s" +
            " WHERE s.location.id = :locationId" +
            " AND mm.member.id = :memberId" +
            " AND mm.status = 'NOT_STARTED'" +
            " AND mm.id < :lastMissionId ORDER BY mm.id DESC")
    Slice<MemberMissionHomeDto> findMemberMissionForHome(
            @Param("locationId") Long locationId,
            @Param("memberId") Long memberId,
            @Param("lastMissionId") Long lastMissionId,
            Pageable pageable);

    @Query("SELECT new com.example.leeseo.domain.mission.dto.MyMissionDoneDto" +
            "(SUM(CASE WHEN mm.status = 'NOT_STARTED' THEN 1 ELSE 0 END), COUNT(mm.id))" +
            "FROM MemberMission mm" )
    Optional<MyMissionDoneDto> getMyMissionDoneCnt();
}
