package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.member.memberId = :memberId ORDER BY r.createdAt DESC")
    List<Review> findAllReviewsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT r FROM Review r WHERE r.member.memberId  = :memberId AND r.mission.missionId = :missionId")
    Optional<Review> findByMemberIdAndMissionId(@Param("memberId") Long memberId, @Param("missionId") Long missionId);

    @Query("SELECT r FROM Review r WHERE r.mission.missionId = :missionId ORDER BY r.createdAt DESC")
    List<Review> findAllReviewsByMissionId(@Param("missionId") Long missionId);
}

