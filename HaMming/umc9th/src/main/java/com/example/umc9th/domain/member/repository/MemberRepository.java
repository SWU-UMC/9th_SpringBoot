package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 닉네임으로 회원 정보 조회
     */
    Optional<Member> findByNickname(String nickname);

    /**
     * 이메일로 회원 정보 조회
     */
    Optional<Member> findByEmail(String email);

    /**
     * 회원의 전체 포인트 합계 조회
     */
    @Query("SELECT SUM(p.amount) FROM Point p WHERE p.member.id = :memberId")
    Integer getTotalPointsByMemberId(@Param("memberId") Long memberId);

    /**
     * 회원이 작성한 리뷰 목록 조회 (최신순)
     */
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByMemberId(@Param("memberId") Long memberId);
}
