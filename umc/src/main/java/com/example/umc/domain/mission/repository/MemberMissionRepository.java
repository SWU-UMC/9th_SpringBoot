package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.enums.MissionStatus;  // MissionStatus enum 임포트
import com.example.umc.domain.mission.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 유저의 미션 목록 조회
    List<MemberMission> findByMember_MemberIdAndStatusOrderByMemberMissionIdDesc(Long memberId, MissionStatus status, Pageable pageable);
}
