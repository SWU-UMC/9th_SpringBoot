package com.example.server_9th.repository;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.mapping.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    //특정 회원의 포인트 전체 내역 조회
    List<Point> findAllByMember(Member member);

    //특정 회원의 포인트 합계 조회
    @Query("SELECT COALESCE(SUM(p.cost), 0) FROM Point p WHERE p.member.id = :user_id")
    Integer getTotalPointByMemberId(Long userId);

}

