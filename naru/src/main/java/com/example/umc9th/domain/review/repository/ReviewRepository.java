package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 사진 없는 리뷰

    // 메서드 이름 기반 쿼리
    // save()는 JpaRepository 기본 제공

    // @Query 기반 쿼리 (명시적 INSERT)
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO review (user_id, store_id, score, content, created_at, updated_at)
        VALUES (:userId, :storeId, :score, :content, NOW(), NOW())
    """, nativeQuery = true)
    void insertReviewWithoutPhotos(
            @Param("userId") Long userId,
            @Param("storeId") Long storeId,
            @Param("score") Double score,
            @Param("content") String content
    );
}
