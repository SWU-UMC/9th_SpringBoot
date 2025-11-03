package com.example.umc.domain.member.repository;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 정보 조회
    Optional<Member> findByEmail(String email);

    // 닉네임으로 회원 정보 조회
    Optional<Member> findByName(String name);

    // 회원의 포인트 합계 조회
    @Query("SELECT COALESCE(SUM(pt.amount), 0) FROM PointTransaction pt WHERE pt.member.memberId = :memberId")
    Integer findTotalPointsByMemberId(@Param("userId") Long memberId);

    // 회원이 작성한 리뷰 목록 조회 (최신순)
    List<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
