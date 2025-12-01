package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    @Query("""
        SELECT r 
        FROM Review r
        WHERE r.member.id = :memberId
        ORDER BY r.id DESC
    """)
    List<Review> findMyReviewsPaged(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
